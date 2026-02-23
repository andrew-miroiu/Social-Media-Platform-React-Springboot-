package com.andrei.springboot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "profiles_java")
public class Profile {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public Profile() {
    }

    public Profile(UUID id, String username, String avatarUrl, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}