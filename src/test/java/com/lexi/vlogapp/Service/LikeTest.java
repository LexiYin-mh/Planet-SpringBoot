package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dao.repository.LikeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LikeTest {

    @Autowired
    private LikeRepository likeRepository;

    @Test
    void testCount(){
        Long countPost = likeRepository.countByPost(3L);

    }
}
