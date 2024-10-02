package com.dis.ms.productms.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        LocalDateTime requestTime = LocalDateTime.now();

        log.info("Incoming request - Time: {}, Method: {}, URI: {}, From IP: {}", requestTime, method, requestURI, remoteAddr);

        String username = request.getRemoteUser();
        if (username != null) {
            log.info("Request made by user: {}", username);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("Completed request for URI: {}", request.getRequestURI());
    }
}