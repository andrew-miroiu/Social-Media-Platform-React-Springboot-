package com.andrei.springboot.repository;

import com.andrei.springboot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // <--- trebuie
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByUserId(String id);

  @Query(
    value = """
        SELECT p.id AS post_id,
       p.text,
       p.image_url,
       p.video_url,
       p.created_at AS created_at, -- nu mai folosi AT TIME ZONE
       p.updated_at AS updated_at,
       pr.id AS user_id,
       pr.username,
       pr.avatar_url,
       COUNT(l.id) AS like_count
        FROM posts p
        LEFT JOIN profiles_java pr ON pr.id::text = p.user_id
        LEFT JOIN likes l ON l.post_id = p.id
        GROUP BY p.id, pr.id;
        """,
    nativeQuery = true
)
List<Object[]> findAllPostsWithCountsNative();
}