package com.andrei.springboot.repository;

import com.andrei.springboot.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Profile findById(String id);
}