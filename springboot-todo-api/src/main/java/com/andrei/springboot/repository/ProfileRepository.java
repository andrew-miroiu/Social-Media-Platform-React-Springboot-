package com.andrei.springboot.repository;

import com.andrei.springboot.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Profile findById(String id);
    List<Profile> findByIdNotOrderByCreatedAtDesc(UUID id);
    Profile findProfileById(UUID id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Profile p SET p.avatarUrl = :url WHERE p.id = :id")
    void updateAvatarUrl(@Param("id") UUID id, @Param("url") String url);
}