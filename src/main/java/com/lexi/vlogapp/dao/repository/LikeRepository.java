package com.lexi.vlogapp.dao.repository;

import com.lexi.vlogapp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

    Boolean deleteByUserIdAndPostId(Long userId, Long postId);

    //Boolean isUserLikePost(Long userId, Long postId);

    Long countByUser(Long userId);

    Long countByPostId(Long postId);

}
