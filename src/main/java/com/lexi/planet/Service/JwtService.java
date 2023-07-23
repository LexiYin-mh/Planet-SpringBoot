package com.lexi.planet.Service;

import com.lexi.planet.dto.UserDto;
import io.jsonwebtoken.Claims;

public interface JwtService {

    String generateToken(UserDto userDto);

    Claims decryptJwtToken(String token);

    boolean isTokenExpired(String token);

    boolean validateAccessToken(String token);
}
