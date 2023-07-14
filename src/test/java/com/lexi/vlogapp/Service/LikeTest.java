package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dao.repository.LikeRepository;
import com.lexi.vlogapp.entity.Like;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LikeTest {

    @Autowired
    private LikeRepository likeRepository;

    @Test
    void testCount(){
        Long countPost = likeRepository.countByPostId(3L);
        System.out.println("=============" + countPost);
    }

    @Test
    void test(){
        List<Like> likes = likeRepository.findAll();
        System.out.println(likes.size());
    }

}
