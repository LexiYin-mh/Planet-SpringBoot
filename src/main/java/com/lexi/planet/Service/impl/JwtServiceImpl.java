package com.lexi.planet.Service.impl;

import com.lexi.planet.Service.JwtService;
import com.lexi.planet.dto.RoleDto;
import com.lexi.planet.dto.UserDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Set;

@Service
public class JwtServiceImpl implements JwtService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt-secret-key}")
    private String SECRET_KEY;
    //private final String SECRET_KEY = System.getenv("secret_key");
    //private final String SECRET_KEY = System.getProperty("secret_key");

    private final long EXPIRATION_TIME = 86400 * 1000; // Expire One Day Later (24 * 60 * 60 = 86400; 1000ms = 1s)

    private String ISSUER = "com.lexi";

    @Override
    public String generateToken(UserDto userDto) {
        /*
        1. decide signature algorithm
        2. Hard Code Secret Key
        3. Organize our payload: Claims ---> map payload   claims has some predefined keys   ,  add your own custom key/value pairs
        4. set claims JWT api
        5. sign JWT with key
        6. generate the token
         */

        logger.info("=====, retrieved SECRET_KEY = {}", SECRET_KEY);

        //decide JWT signature algorithm to be used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        //create and organize claim
        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(userDto.getId()));
        claims.setSubject(userDto.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        // Populate Allowed Resources To Claim (set claims JWT api)
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdatedResources = "";
        String allowedDeleteResources = "";

        Set<RoleDto> roleDtoSet = userDto.getRoleDtos();
        for (RoleDto roleDto: roleDtoSet){
            if(roleDto.isAllowedRead()){
                allowedReadResources = String.join(",", roleDto.getAllowedResources(), allowedReadResources);
            }
            if(roleDto.isAllowedCreate()){
                allowedCreateResources = String.join(",", roleDto.getAllowedResources(), allowedCreateResources);
            }
            if(roleDto.isAllowedUpdate()){
                allowedUpdatedResources = String.join(",", roleDto.getAllowedResources(), allowedUpdatedResources);
            }
            if(roleDto.isAllowedDelete()) {
                allowedDeleteResources = String.join(",", roleDto.getAllowedResources(), allowedDeleteResources);
            }
        }
        logger.info("=======, The final result of allowedReadResources = {}", allowedReadResources);
        logger.info("=======, The final result of allowedCreateResources = {}", allowedCreateResources);
        logger.info("=======, The final result of allowedUpdateResources = {}", allowedUpdatedResources);
        logger.info("=======, The final result of allowedDeleteResources = {}", allowedDeleteResources);

        claims.put("allowedReadResources", allowedReadResources.replaceAll(",$", ""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(",$", ""));
        claims.put("allowedUpdatedResources", allowedUpdatedResources.replaceAll(",$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(",$", ""));

        // sign JWT with key and generate token
        byte[] secretKeyStringBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, secretKeyStringBytes);
        String generatedToken = jwtBuilder.compact();

        return generatedToken;
    }

    @Override
    public Claims decryptJwtToken(String token) {
        // convert Based64-coded string to byte array since JWT parser need Bytes for processing
        byte[] secretKeyStringBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        //
        Claims claims = Jwts.parser()
                .setSigningKey(secretKeyStringBytes)
                .parseClaimsJws(token)
                .getBody();    // 这里可能会throw exception;
        logger.info("=== parse claims = {}", claims);
        return claims;
    }

    @Override
    public boolean isTokenExpired(String token) {

        boolean isTokenExpired = true;

        try{
            Claims claims = decryptJwtToken(token);
            Date tokenExpirationDate = claims.getExpiration();
            Date nowDate = new Date();

            isTokenExpired = nowDate.after(tokenExpirationDate);
        } catch (ExpiredJwtException e){
            logger.error("========= ExpiredJwtException is caught, error = {}", e.getMessage());
        }

        return isTokenExpired;
    }

    @Override
    public boolean validateAccessToken(String token) {

        boolean isTokenValid = false;

        try {
            byte[] secretKeyStringBytes = DatatypeConverter.parseBase64Binary(String.valueOf(SECRET_KEY));

            // create Jwt parser, set signingKey, 然后尝试解析和验证传入的token
            // 如果JWT有效 （即，它是又正确的密钥签名的，且尚未过期， 且。。。），这个方法会正常运行
            // 不然就会throw exception
            Jwts.parser()
                    .setSigningKey(secretKeyStringBytes)
                    .parseClaimsJws(token); // 这里可能会throw exception
            isTokenValid = true;

        } catch (ExpiredJwtException ex) {
            logger.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            logger.error("Signature validation failed");
        }
        return isTokenValid;
    }
}
