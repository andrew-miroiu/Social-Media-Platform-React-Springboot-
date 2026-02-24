package com.andrei.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrei.springboot.dto.ProfileResponseDTO;
import com.andrei.springboot.service.ProfileService;

import java.util.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileResponseDTO> getAllProfiles(){
        return profileService.getAllProfiles();
    }

    @GetMapping("/getOwnProfile")
    public ProfileResponseDTO getOwnProfile(){
        return profileService.getOwnProfile();
    }

}