package com.lexi.vlogapp.Service.impl;

import com.lexi.vlogapp.Service.HashtagService;
import com.lexi.vlogapp.dao.repository.HashtagRepository;
import com.lexi.vlogapp.dto.HashtagDto;
import com.lexi.vlogapp.dto.PostDto;
import com.lexi.vlogapp.entity.Hashtag;
import com.lexi.vlogapp.entity.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HashtagServiceImplTest {

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private HashtagRepository hashtagRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        Set<HashtagDto> hashtagDtos = hashtagService.getAll();
        assertNotEquals(0, hashtagDtos.size());
        System.out.println(hashtagDtos.size());
    }

    @Test
    void tryOut() {
//        Boolean flag = hashtagRepository.existsByHashtagContent("technology");
//        assertTrue(flag);

        Boolean flag = hashtagService.deleteById(1L);
        assertTrue(flag);

//        Boolean flag1 = hashtagService.deleteByHashtagContent("technology");
//        assertTrue(flag1);
    }





}