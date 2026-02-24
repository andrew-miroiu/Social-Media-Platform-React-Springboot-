package com.andrei.springboot.service;

import com.andrei.springboot.model.Comment;
import com.andrei.springboot.dto.CommentResponseDTO;
import com.andrei.springboot.dto.CreateCommentRequestDTO;


import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<CommentResponseDTO> getCommentsByPostId(UUID postId);
    CommentResponseDTO createComment(CreateCommentRequestDTO request);
}