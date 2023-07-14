package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dao.repository.PostRepository;
import com.lexi.vlogapp.entity.Post;
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
