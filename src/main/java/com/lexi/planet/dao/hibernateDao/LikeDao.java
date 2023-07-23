package com.lexi.planet.dao.hibernateDao;

import com.lexi.planet.entity.Like;

public interface LikeDao extends GenericDao<Like, Long> {

    Boolean deleteByUserIdAndPostId(Long userId, Long postId);

    //Boolean isUserLikePost(Long userId, Long postId);

    Long countByUser(Long userId);

    Long countByPost(Long postId);

}
