package com.andrei.springboot.repository;

import com.andrei.springboot.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;
import java.util.*;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    
    List<Comment> findByPostId(UUID postId);
}