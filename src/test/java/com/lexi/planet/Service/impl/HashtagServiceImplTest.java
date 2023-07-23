package com.lexi.planet.Service.impl;

import com.lexi.planet.Service.HashtagService;
import com.lexi.planet.dao.repository.HashtagRepository;
import com.lexi.planet.dto.HashtagDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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