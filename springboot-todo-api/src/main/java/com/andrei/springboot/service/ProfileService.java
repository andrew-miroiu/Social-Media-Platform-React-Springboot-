package com.andrei.springboot.service;

import com.andrei.springboot.model.Profile;
import com.andrei.springboot.dto.ProfileResponseDTO;

import java.util.List;

public interface ProfileService {
    List<ProfileResponseDTO> getAllProfiles();
}