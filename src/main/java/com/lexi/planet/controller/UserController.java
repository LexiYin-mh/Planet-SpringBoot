package com.lexi.planet.controller;

import com.lexi.planet.Service.UserService;
import com.lexi.planet.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Set<UserDto>> getAllUsers() {
        Set<UserDto> userDtos = userService.getAll();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/users/one")
    public Set<UserDto> getAllUserSet() {
        Set<UserDto> userDtos = userService.getAll();
        return userDtos;
    }
}

