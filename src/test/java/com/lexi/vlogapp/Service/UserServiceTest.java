package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach

    @Test
    void findAll() {
        Set<User> users = userService.findAll();
        System.out.println(users.size());
        assertNotNull(users);
        assertEquals(8, users.size());
    }

    @Test
    void saveUser() {
        User savedUser = userService.save(testUser);

    }
}