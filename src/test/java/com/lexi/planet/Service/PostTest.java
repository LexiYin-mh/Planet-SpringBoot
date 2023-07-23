package com.lexi.planet.Service;

import com.lexi.planet.dao.repository.PostRepository;
import com.lexi.planet.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void tryout(){
        Set<Post> posts = postRepository.findByUserId(4L);
        System.out.println(posts);
    }
}
