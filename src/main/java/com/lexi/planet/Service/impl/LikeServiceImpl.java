package com.lexi.planet.Service.impl;

import com.lexi.planet.Service.LikeService;
import com.lexi.planet.Service.NotificationService;
import com.lexi.planet.dao.repository.LikeRepository;
import com.lexi.planet.dao.repository.PostRepository;
import com.lexi.planet.dao.repository.UserRepository;
import com.lexi.planet.dto.LikeDto;
import com.lexi.planet.entity.Like;
import com.lexi.planet.entity.Post;
import com.lexi.planet.entity.User;
import com.lexi.planet.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MapperUtil mapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    @Transactional
    public LikeDto likePost(Long userId, Long postId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isPresent() && post.isPresent()) {
            Like like = new Like();
            like.setUser(user.get());
            like.setPost(post.get());
            like.setLikeDate(new Timestamp(System.currentTimeMillis())); // set current timestamp
            like = likeRepository.save(like);
            // Convert to LikeDto and return
            return mapper.convertLikeToDto(like);
        } else {
            throw new RuntimeException("User or post not found");
        }

    }

    @Override
    @Transactional
    public Boolean unlikePost(Long userId, Long postId) {
        Optional<Like> like = likeRepository.findByUserIdAndPostId(userId, postId);
        Boolean flag = false;
        if(like.isPresent()) {
            likeRepository.delete(like.get());
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sendNotificationToUser(Long userId, String message) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            notificationService.sendLikeNotification(user.get(), message);
            return true;
        } else {
            return false;
        }
    }


}
