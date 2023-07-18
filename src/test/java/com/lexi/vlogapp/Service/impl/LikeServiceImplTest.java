package com.lexi.vlogapp.Service.impl;

import com.lexi.vlogapp.Service.LikeService;
import com.lexi.vlogapp.dao.repository.LikeRepository;
import com.lexi.vlogapp.entity.Like;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeServiceImplTest {

    @Autowired
    private LikeService likeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

}