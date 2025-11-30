package com.project.give.security.filter;

import com.project.give.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends GenericFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // OPTIONS는 무조건 허용
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // permitAll URL 정의
        List<String> permitUrls = List.of(
                "/auth",
                "/auth/**",
                "/users",
                "/users/**",
                "/account/find-username",
                "/account/username-check",
                "/account/nickname-check",
                "/account/passwordReset",
                "/donations",
                "/donations/**",
                "/categories",
                "/categories/**",
                "/donation-project-details",
                "/donation-project-details/**",
                "/donation-project-contributions",
                "/donation-project-contributions/**",
                "/donation-project-comments",
                "/donation-project-comments/**",
                "/fundings",
                "/fundings/**",
                "/store/products",
                "/store/products/**",
                "/main",
                "/main/**",
                "/server",
                "/server/**"
        );

        String uri = request.getRequestURI();

        // permitAll 체크
        for (String permit : permitUrls) {
            if (uri.startsWith(permit.replace("/**", ""))) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // JWT 검사
        String accessToken = request.getHeader("Authorization");
        String token = jwtProvider.removeBearer(accessToken);

        if (token == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        Claims claims;
        try {
            claims = jwtProvider.getClaims(token);
        } catch (Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        Authentication authentication = jwtProvider.getAuthentication(claims);
        if (authentication == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
