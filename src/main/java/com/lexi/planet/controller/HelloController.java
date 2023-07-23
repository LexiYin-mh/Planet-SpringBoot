package com.lexi.planet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String sayHello() {
        String helloString = "if you can see this, the app is working now!";
        return helloString;
    }

}
