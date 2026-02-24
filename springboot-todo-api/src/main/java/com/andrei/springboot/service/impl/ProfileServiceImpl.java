package com.andrei.springboot.service.impl;

import com.andrei.springboot.model.Profile;
import com.andrei.springboot.service.ProfileService;
import com.andrei.springboot.security.CustomUserDetails;
import com.andrei.springboot.repository.ProfileRepository;
import com.andrei.springboot.dto.ProfileResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
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
}