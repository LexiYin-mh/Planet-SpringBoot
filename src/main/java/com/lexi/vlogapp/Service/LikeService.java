package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dto.LikeDto;
import com.lexi.vlogapp.entity.Like;


//public interface LikeService extends CrudService<LikeDto, Long>{
public interface LikeService {

    LikeDto likePost(Long userId, Long postId);

    Boolean unlikePost(Long userId, Long postId);

    Boolean sendNotificationToUser(Long userId, String message);

}
