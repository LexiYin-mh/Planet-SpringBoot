package com.lexi.planet.Service;

import com.lexi.planet.dto.LikeDto;


//public interface LikeService extends CrudService<LikeDto, Long>{
public interface LikeService {

    LikeDto likePost(Long userId, Long postId);

    Boolean unlikePost(Long userId, Long postId);

    Boolean sendNotificationToUser(Long userId, String message);

}
