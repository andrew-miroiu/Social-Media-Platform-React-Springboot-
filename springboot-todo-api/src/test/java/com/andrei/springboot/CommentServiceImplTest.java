package com.andrei.springboot;

import com.andrei.springboot.dto.CommentResponseWithUsernameDTO;
import com.andrei.springboot.dto.PostResponseDTO;
import com.andrei.springboot.model.Comment;
import com.andrei.springboot.model.Post;
import com.andrei.springboot.repository.CommentRepository;
import com.andrei.springboot.repository.PostRepository;
import com.andrei.springboot.repository.ProfileRepository;
import com.andrei.springboot.service.StorageService;
import com.andrei.springboot.service.impl.CommentServiceImpl;
import com.andrei.springboot.service.impl.PostServiceImpl;
import com.andrei.springboot.model.Profile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void getAllComments_shouldReturnEmptyList_whenNoCommentsExist(){

        UUID postId = UUID.randomUUID();

        when(commentRepository.findCommentsWithUsernameByPostId(postId)).thenReturn(List.of());

        List<CommentResponseWithUsernameDTO> result =  commentService.getCommentsByPostId(postId);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getAllComments_shouldReturnCommentWithUsername_whenCommentExists(){
        UUID commentId = UUID.randomUUID();
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Object[] row = new Object[]{
            commentId,           // row[0] - comment id
            postId,              // row[1] - post id
            userId.toString(),   // row[2] - user id
            "comment unit test", // row[3] - text
            "andrei",            // row[4] - username
            Instant.now()        // row[5] - created_at
        };

        List<Object[]> rows = new ArrayList<>();
        rows.add(row);

        when(commentRepository.findCommentsWithUsernameByPostId(postId)).thenReturn(rows);

        List<CommentResponseWithUsernameDTO> result = commentService.getCommentsByPostId(postId);

        assertEquals(1, result.size());
        assertEquals("comment unit test", result.get(0).getText());
        assertEquals("andrei", result.get(0).getUsername());
    }

    @Test
    void getAllComments_shouldReturnCommentsWithUsername_whenMultipleCommentExist(){
        UUID commentId1 = UUID.randomUUID();
        UUID commentId2 = UUID.randomUUID();
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Object[] row1 = new Object[]{
            commentId1,           // row[0] - comment id
            postId,              // row[1] - post id
            userId.toString(),   // row[2] - user id
            "comment unit test1", // row[3] - text
            "andrei1",            // row[4] - username
            Instant.now()        // row[5] - created_at
        };

        Object[] row2 = new Object[]{
            commentId2,           // row[0] - comment id
            postId,              // row[1] - post id
            userId.toString(),   // row[2] - user id
            "comment unit test2", // row[3] - text
            "andrei2",            // row[4] - username
            Instant.now()        // row[5] - created_at
        };

        List<Object[]> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        when(commentRepository.findCommentsWithUsernameByPostId(postId)).thenReturn(rows);

        List<CommentResponseWithUsernameDTO> result = commentService.getCommentsByPostId(postId);

        assertEquals(2, result.size());
        assertEquals("comment unit test1", result.get(0).getText());
        assertEquals("comment unit test2", result.get(1).getText());
        assertEquals("andrei1", result.get(0).getUsername());
        assertEquals("andrei2", result.get(1).getUsername());
    }

}
