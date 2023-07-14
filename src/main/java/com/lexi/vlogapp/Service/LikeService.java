package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.entity.Like;

public interface LikeService extends CrudService{

    Like likePost(Long userId, Long postId);

    Boolean unlikePost(Long userId, Long postId);

    Boolean sendNotificationToUser(Long userId, String message);
}
