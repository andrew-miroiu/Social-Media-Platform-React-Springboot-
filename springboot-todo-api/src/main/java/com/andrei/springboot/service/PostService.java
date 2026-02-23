package com.andrei.springboot.service;

import com.andrei.springboot.dto.PostResponseDTO;
import com.andrei.springboot.dto.PostWithCountsDTO;
import com.andrei.springboot.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    
    List<PostResponseDTO> getAllPosts();
    List<PostResponseDTO> getPostsByUser(String id);
    List<PostWithCountsDTO> getPostsWithCounts();

}