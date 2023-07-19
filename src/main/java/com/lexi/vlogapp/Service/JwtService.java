package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dto.UserDto;
import io.jsonwebtoken.Claims;

public interface JwtService {

    String generateToken(UserDto userDto);

    Claims decryptJwtToken(String token);

    boolean isTokenExpired(String token);

    boolean validateAccessToken(String token);
}
