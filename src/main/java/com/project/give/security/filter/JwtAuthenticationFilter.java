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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        List<String> antMatchers = List.of(
                "/users",
                "/auth/login",
                "/donations", "/donations/",
                "/donation-categories",
                "/donation-project-details", "/donation-project-details/",
                "/donation-project-contributions", "/donation-project-contributions/",
                "/donation-project-comments", "/donation-project-comments/"
        ); // 수정예정

        String uri = request.getRequestURI();

            request.setAttribute("isPermitAll", false);

        for(String antMatcher : antMatchers) {
            if(uri.startsWith(antMatcher)) {
                request.setAttribute("isPermitAll", true);
            }
        }

        Boolean isPermitAll = (Boolean) request.getAttribute("isPermitAll");

        if(!isPermitAll) {
            String accessToken = request.getHeader("Authorization");
            String removeBearerToken = jwtProvider.removeBearer(accessToken);
            if (removeBearerToken == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            Claims claims = null;
            try {
                claims = jwtProvider.getClaims(removeBearerToken);
            } catch (ExpiredJwtException e) {
                System.out.println("JWT 만료: " + e.getMessage());
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "JWT 만료");
                return;
            } catch (Exception e) {
                System.out.println("JWT 검증 실패: " + e.getMessage());
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            Authentication authentication = jwtProvider.getAuthentication(claims);
            if(authentication == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
