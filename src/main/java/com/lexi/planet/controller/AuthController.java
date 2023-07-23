package com.lexi.planet.controller;

import com.lexi.planet.Service.JwtService;
import com.lexi.planet.Service.UserService;
import com.lexi.planet.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/auth", "/planet/auth"})
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> authenticate(@RequestBody UserDto userDto){

        //1. validate username/password
        //400 bad request
        //2. generate token
        //200 or 500

        String token = "";
        Map<String, String> resultMap = new HashMap<>();

        //这段代码尝试使用请求中的用户DTO的邮箱和密码来获取用户。如果获取失败，那么就向resultMap中添加一条消息，然后返回一个状态码为401的HTTP响应，响应的正文是resultMap。
        try{
            UserDto retrievedUserDto = userService.getUserWithRolesByCredential(userDto.getEmail(), userDto.getPassword());
            if(retrievedUserDto == null){
                resultMap.put("msg", "The input user email and password are incorrect");
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(resultMap);
            }

            // 如果用户验证成功，那么这段代码会使用JwtService来为这个用户生成一个JWT token，并将这个token添加到resultMap中。
            token = jwtService.generateToken(retrievedUserDto);
            resultMap.put("token", token);

        } catch (Exception e){
            logger.error("token", token);
            String errorMsg = e.getMessage();
            resultMap.put("msg", errorMsg);
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(resultMap);
        }

        // 如果一切正常，那么这个方法将返回一个状态码为200的HTTP响应，响应的正文
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(resultMap);

    }
}
