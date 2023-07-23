package com.lexi.planet.dao.repository;

import com.lexi.planet.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    // This retrieval is case sensitive
    Set<Post> findByPostContentContaining(String keyword);

    Set<Post> findByUserId(Long id);

    @Query("select p from Post p where p.user.id = :userId order by p.postDate desc")
    List<Post> findTopByUserIdOrderByPostDateDesc(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT p FROM Post p ORDER BY p.likeNum DESC")
    List<Post> findTopPosts(Pageable pageable);

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.user LEFT JOIN FETCH p.hashtags WHERE p.id = :postId")
    Optional<Post> findWithUserAndHashtagsById(@Param("postId") Long postId);

    @Query("SELECT p.id FROM Post p JOIN p.hashtags h WHERE h.id = :hashtagId")
    List<Long> findPostIdsByHashtagId(@Param("hashtagId") Long hashtagId);

}
