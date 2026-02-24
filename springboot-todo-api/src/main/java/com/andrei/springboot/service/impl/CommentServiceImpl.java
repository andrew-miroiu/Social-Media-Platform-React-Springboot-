package com.andrei.springboot.service.impl;

import com.andrei.springboot.service.CommentService;
import com.andrei.springboot.repository.CommentRepository;
import org.springframework.stereotype.Service;
import com.andrei.springboot.dto.CommentResponseDTO;
import com.andrei.springboot.dto.CreateCommentRequestDTO;
import com.andrei.springboot.model.Comment;
import com.andrei.springboot.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override 
    public List<CommentResponseDTO> getCommentsByPostId(UUID postId){
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(comment -> new CommentResponseDTO(
                        comment.getId(),
                        comment.getPostId(),
                        comment.getUserId(),
                        comment.getText(),
                        comment.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public CommentResponseDTO createComment(CreateCommentRequestDTO request){

        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = userDetails.getId().toString();

        Comment comment = new Comment(request.getPostId(), userId, request.getText());

        Comment saved = commentRepository.save(comment);

        return new CommentResponseDTO(saved.getId(), saved.getPostId(), saved.getUserId(), saved.getText(), saved.getCreatedAt());
    }

}