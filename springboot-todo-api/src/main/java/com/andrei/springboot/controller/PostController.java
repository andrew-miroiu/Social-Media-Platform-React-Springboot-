package com.andrei.springboot.controller;

import com.andrei.springboot.dto.PostResponseDTO;
import com.andrei.springboot.dto.PostWithCountsDTO;
import com.andrei.springboot.model.Post;
import com.andrei.springboot.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts-with-counts")
    public List<PostWithCountsDTO> getPostsWithCounts() {
        return postService.getPostsWithCounts();
    }

    @GetMapping("/{id}")
    public List<PostResponseDTO> getPostsByUser(@PathVariable String id) {
        return postService.getPostsByUser(id);
    }
}