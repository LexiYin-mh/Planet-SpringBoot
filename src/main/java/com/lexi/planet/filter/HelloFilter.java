//package com.lexi.planet.filter;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.Instant;
//
//@WebFilter(filterName = "myHelloFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
//@Component
//public class HelloFilter implements Filter {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        Instant startTime = Instant.now();
//
//        HttpServletRequest req = (HttpServletRequest) servletRequest;  // Cast Your Request To Http Request
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        logger.info("1111111111 pre action from MySecondFilter, remoteHost={}", req.getRemoteHost());
//        logger.info("1111111111 pre action from MySecondFilter, RequestURI={}", req.getRequestURI());
//        logger.info("1111111111 pre action from MySecondFilter, Header={}", req.getHeader("testHeader"));
//
//        filterChain.doFilter(req, resp);
//
//        logger.info("1111111111 post action from MySecondFilter, status={}", resp.getStatus());
//        logger.info("1111111111 post action from MySecondFilter, Headernames={}", resp.getHeaderNames());
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
