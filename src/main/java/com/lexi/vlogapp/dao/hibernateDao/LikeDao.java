package com.lexi.vlogapp.dao.hibernateDao;

import com.lexi.vlogapp.entity.Like;

public interface LikeDao extends GenericDao<Like, Long> {

    Boolean deleteByUserIdAndPostId(Long userId, Long postId);

    //Boolean isUserLikePost(Long userId, Long postId);

    Long countByUser(Long userId);

    Long countByPost(Long postId);

}
