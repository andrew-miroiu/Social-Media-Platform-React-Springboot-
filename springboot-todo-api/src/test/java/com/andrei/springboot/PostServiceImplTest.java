package com.andrei.springboot;

import com.andrei.springboot.dto.PostResponseDTO;
import com.andrei.springboot.model.Post;
import com.andrei.springboot.repository.PostRepository;
import com.andrei.springboot.repository.ProfileRepository;
import com.andrei.springboot.service.StorageService;
import com.andrei.springboot.service.impl.PostServiceImpl;
import com.andrei.springboot.model.Profile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void getAllPosts_shouldReturnEmptyList_whenNoPostsExist() {
        // Arrange
        when(postRepository.findAll()).thenReturn(List.of());

        // Act
        List<PostResponseDTO> result = postService.getAllPosts();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getAllPosts_shouldReturnPostWithUsername_whenProfileExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID postId = UUID.randomUUID();

        Post post = new Post();
        post.setId(postId);
        post.setText("Hello world");
        post.setUserId(userId.toString());
        post.setCreatedAt(OffsetDateTime.now());
        post.setUpdatedAt(OffsetDateTime.now());

        Profile profile = new Profile();
        profile.setId(userId);
        profile.setUsername("andrei");

        when(postRepository.findAll()).thenReturn(List.of(post));
        when(profileRepository.findById(userId)).thenReturn(Optional.of(profile));

        // Act
        List<PostResponseDTO> result = postService.getAllPosts();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Hello world", result.get(0).getText());
        assertEquals("andrei", result.get(0).getUsername());
    }

    @Test
    void getAllPosts_shouldReturnPostWithoutUsername_whenProfileDoesntExist(){
        
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Post post = new Post();
        post.setId(postId);
        post.setText("Hello world");
        post.setUserId(userId.toString());
        post.setCreatedAt(OffsetDateTime.now());
        post.setUpdatedAt(OffsetDateTime.now());

        when(postRepository.findAll()).thenReturn(List.of(post));
        when(profileRepository.findById(userId)).thenReturn(Optional.empty());

        List<PostResponseDTO> result = postService.getAllPosts();

        assertEquals(1, result.size());
        assertEquals("Hello world", result.get(0).getText());
        assertEquals("Unknown", result.get(0).getUsername());
    }

}