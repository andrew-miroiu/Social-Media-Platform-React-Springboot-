package com.andrei.springboot.service.impl;

import com.andrei.springboot.model.Profile;
import com.andrei.springboot.service.ProfileService;
import com.andrei.springboot.security.CustomUserDetails;
import com.andrei.springboot.repository.ProfileRepository;
import com.andrei.springboot.dto.ProfileResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.andrei.springboot.service.StorageService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final StorageService storageService;

    public ProfileServiceImpl(ProfileRepository profileRepository, @Qualifier("profilePictureStorageService") StorageService storageService){
        this.profileRepository = profileRepository;
        this.storageService = storageService;
    }

    @Override
    public List<ProfileResponseDTO> getAllProfiles(){
        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UUID userId = userDetails.getId();

        List<Profile> profiles = profileRepository.findByIdNotOrderByCreatedAtDesc(userId);

        return profiles.stream()
                .map(profile -> new ProfileResponseDTO(
                    profile.getId(),
                    profile.getUsername(),
                    profile.getAvatarUrl(),
                    profile.getCreatedAt()
                ))
                .toList();       


    }

    @Override
    public ProfileResponseDTO getOwnProfile(){
        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UUID userId = userDetails.getId();

        Profile profile = profileRepository.findProfileById(userId);

        return new ProfileResponseDTO(
                profile.getId(),
                profile.getUsername(),
                profile.getAvatarUrl(),
                profile.getCreatedAt()
        );
    }

    @Override
    public String updateProfile(MultipartFile file) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID userId = userDetails.getId();

        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("File is required");
        }

        if(!file.getContentType().startsWith("image/")){
            throw new IllegalArgumentException("File must be an image");
        }

        String oldAvatarUrl = profileRepository.findProfileById(userId).getAvatarUrl();
        String newAvatarUrl;

        try {
            newAvatarUrl = storageService.uploadFile(file);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload profile picture", e);
        }

        profileRepository.updateAvatarUrl(userId, newAvatarUrl);

        if (oldAvatarUrl != null && !oldAvatarUrl.isBlank()) {
            try {
                String oldPath = extractPathFromUrl(oldAvatarUrl);
                storageService.deleteFile(oldPath);
            } catch (Exception e) {
                System.out.println("Failed to delete old avatar: " + e.getMessage());
            }
        }

        return newAvatarUrl;
    }

    private String extractPathFromUrl(String url) {
        String marker = "/storage/v1/object/public/socialMediaApp/profile_pictures/";
        int index = url.indexOf(marker);

        if(index == -1){
            throw new IllegalArgumentException("Invalid Supabase URL");
        }

        return url.substring(index + marker.length());
    }

}