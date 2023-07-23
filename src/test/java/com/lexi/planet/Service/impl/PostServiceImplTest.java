package com.lexi.planet.Service.impl;

import com.lexi.planet.Service.PostService;
import com.lexi.planet.Service.UserService;
import com.lexi.planet.dao.repository.PostRepository;
import com.lexi.planet.dto.PostDto;
import com.lexi.planet.dto.UserDto;
import com.lexi.planet.exception.InvalidCredentialsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    private UserDto testUserDto;

    private PostDto testPost1Dto;

    @BeforeEach
    void setUp() {
        // make up fake data for testing.
        // First, save One Side
        testUserDto = userService.getById(5L);
        //userRepository.save(testUser);

        // Second, save Many Side
        testPost1Dto = new PostDto();
        testPost1Dto.setPostContent("test1");
        testPost1Dto.setPostMedia("test/media/1");
        testPost1Dto.setPostDate(Timestamp.valueOf(LocalDateTime.now()));
        testPost1Dto.setUserDto(testUserDto);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        PostDto savedPostDto = postService.create(testPost1Dto);
        System.out.println(savedPostDto);
    }

    @Test
    void create_throw_exception() {
        testUserDto = new UserDto();
        testUserDto.setId(100L);
        testUserDto.setName("testUserDto");
        testPost1Dto.setUserDto(testUserDto);

        assertThrows(InvalidCredentialsException.class, () -> { postService.create(testPost1Dto); });
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        Boolean flag = postService.delete(testPost1Dto);
        assertTrue(flag);
    }

    @Test
    void deleteById() {
        Boolean flag = postService.deleteById(1L);
        assertTrue(flag);
    }

    @Test
    void testGetPost() {
        Set<PostDto> postDtos = postService.getPostsByHashtagId(3L);
        System.out.println(postDtos);
    }


}