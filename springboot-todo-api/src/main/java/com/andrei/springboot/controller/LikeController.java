package com.andrei.springboot.controller;

import com.andrei.springboot.dto.PostResponseDTO;
import com.andrei.springboot.model.Post;
import com.andrei.springboot.model.Like;
import com.andrei.springboot.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    /* private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    } */

    

}