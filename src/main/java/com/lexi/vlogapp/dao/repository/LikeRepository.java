package com.lexi.vlogapp.dao.repository;

import com.lexi.vlogapp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Boolean deleteByUserIdAndPostId(Long userId, Long postId);

    //Boolean isUserLikePost(Long userId, Long postId);

    Long countByUser(Long userId);

    Long countByPostId(Long postId);

}
