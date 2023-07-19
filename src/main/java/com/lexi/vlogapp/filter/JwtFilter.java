package com.lexi.vlogapp.filter;

import com.lexi.vlogapp.Service.JwtService;
import com.lexi.vlogapp.Service.UserService;
import com.lexi.vlogapp.Service.impl.JwtServiceImpl;
import com.lexi.vlogapp.dto.UserDto;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JwtFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
@Component
public class JwtFilter extends OncePerRequestFilter {
// Once Filter Per Request

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Value("${AUTH_URI}")
    private String AUTH_URI;

    @Value("${AUTH_URI_EXTERNAL}")
    private String AUTH_URI_EXTERNAL;

    /*
     * When the app is deployed to external Tomcat, Spring DI unable to fulfilled because filter is managed by servlet instead of Spring and DI can not be done by Spring features (@Autowire).
     * therefore, we need to override the following method which inherited from GenericFilterBean.class to initialized the DI components manually.
     */
    @Override
    public void initFilterBean() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext()); // implement manual dependency injection
    }

//    @Override
//    public void initFilterBean() throws ServletException {
//
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        //reference to bean from app context
//        jwtService = webApplicationContext.getBean(JwtServiceImpl.class);
//        userService = webApplicationContext.getBean(UserService.class);
//    }
    // Another way to fetch Spring Bean, but the upper one is more concise

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Do Authorization For Request
        int statusCode = authorization(request);

        if(statusCode == HttpServletResponse.SC_ACCEPTED) {
            filterChain.doFilter(request, response);
        } else {
            // If the statusCode is not 202 (the request is denied), we send an error response to the client.
            response.sendError(statusCode);
        }

    }

    // What You Are Allowed To Do
    private int authorization(HttpServletRequest request) {

        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;

        String incomingUri = request.getRequestURI();
        logger.info("====, within authorization(...), incomingUri = {}", incomingUri);

        /*
            If the URI that we are accessing is /auth or external auth, return 202 directly for generating token
            If the URI is not Auth, then we check the token in the header of the request
            For how to check the token:
            1. check if the whole token exist
            2. get token, and check if token exist
            3. decrypt JwtToken to claims and check if claims is empty
            4. fetch user info and check the allowed resources in the claim
         */

        if(isIncomingUriEqualToAuth(incomingUri)) {
            statusCode = HttpServletResponse.SC_ACCEPTED;
            return statusCode;
        }

        try {
            String wholeToken = request.getHeader("Authorization");

            // Check if the whole token exist
            if (wholeToken != null || !wholeToken.trim().isEmpty()){   //trim is used to remove the first and last space
                String token = wholeToken.split(" ")[1].trim();
                logger.info("=========, whithin authorization, token = {}", token);

                // check if token exist, if it's exist, decrypt it and get claims
                if (token == null || token.trim().isEmpty()) return HttpServletResponse.SC_UNAUTHORIZED;

                Claims claims = jwtService.decryptJwtToken(token);

                // check if claim valid
                if(claims == null || claims.isEmpty()) return HttpServletResponse.SC_UNAUTHORIZED;

                // May not be necessary
                if(!isUserRetrievedByUserIdValid(claims.getId())){
                    return HttpServletResponse.SC_UNAUTHORIZED;
                }

                String httpMethodValue = request.getMethod();
                boolean isRequestUriAllowedToBeAccessed = checkRequestUriAuthorization(claims, httpMethodValue, incomingUri);
                if (isRequestUriAllowedToBeAccessed) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                }
            }
        } catch (Exception e) {
            logger.error("=============== Exception is thrown when parsing JWT token, error = {}", e.getMessage());
        }

        return statusCode;
    }

    private boolean checkRequestUriAuthorization(Claims claims, String httpMethodValue, String requestUri) {

        boolean isAuthorized = false;
        String allowedResources = findAllowedResourcesUsingHttpMethodValueWithClaims(claims, httpMethodValue);
        String[] allowedResourcesArray = allowedResources.split(",");
        for(String eachAllowedResources: allowedResourcesArray){
            logger.info("==========, requestUri = {}, eachAllowedResource={}", requestUri, eachAllowedResources);
            if(requestUri.trim().toLowerCase().startsWith(eachAllowedResources.trim().toLowerCase())){
                isAuthorized = true;
                break;
            }
        }
        return isAuthorized;
    }

    private String findAllowedResourcesUsingHttpMethodValueWithClaims(Claims claims, String httpMethodValue) {

        String allowedResources = "";
        switch (httpMethodValue){
            case "GET":
                allowedResources = (String) claims.get("allowedReadResources");
            case "POST":
                allowedResources = (String) claims.get("allowedCreateResources");
            case "PUT":
                allowedResources = (String) claims.get("allowedUpdatedResources");
            case "PATCH":
                allowedResources = (String) claims.get("allowedUpdatedResources");
            case "DELETE":
                allowedResources = (String) claims.get("allowedDeleteResources");
        }
        return allowedResources;

    }

    private boolean isUserRetrievedByUserIdValid(String id) {
        boolean isValid = false;
        if(id !=null){
            UserDto userDto = userService.getById(Long.valueOf(id));
            if(userDto != null){
                isValid = true;
                logger.info("=====, Now using userID={}, retrieved UserDto={}", id, userDto);
            }
        }
        return isValid;
    }

    private boolean isIncomingUriEqualToAuth(String incomingUri) {

        boolean isIncomingUriEqualToAuth = false;
        if(incomingUri.equalsIgnoreCase(AUTH_URI) || incomingUri.equalsIgnoreCase(AUTH_URI_EXTERNAL)){
            isIncomingUriEqualToAuth = true;
        }
        return isIncomingUriEqualToAuth;
    }
}
