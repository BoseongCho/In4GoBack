package com.boseongcho.in4goback.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response
            , AccessDeniedException accessDeniedException) throws IOException, ServletException {

        /* 유효한 자격증명을 제공하지 않고 접근 시 401 상태코드 발생 */
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}


