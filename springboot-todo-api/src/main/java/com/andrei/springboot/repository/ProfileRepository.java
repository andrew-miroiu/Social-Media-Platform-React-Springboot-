package com.andrei.springboot.repository;

import com.andrei.springboot.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Profile findById(String id);
    List<Profile> findByIdNotOrderByCreatedAtDesc(UUID id);
    Profile findProfileById(UUID id);
}