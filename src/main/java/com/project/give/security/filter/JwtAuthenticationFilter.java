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
        
        // 인증이 필요없는 페이지 (회원가입, 로그인, 아이디찾기, 비밀번호 찾기, 중복체크 등) - 기부페이지는 개발이후 제외 예정
        List<String> antMatchers = List.of(
                "/users",
                "/auth/login",
                "/donations",
                "/categories",
                "/donation-project-details", "/donation-project-details/",
                "/donation-project-contributions", "/donation-project-contributions/",
                "/donation-project-comments", "/donation-project-comments/",
                "/account/find-username",
                "/account/passwordReset",
                "/account/username-check", 
                "/account/nickname-check",
                "/fundings",
                "/store/products",
                "/main",
                "/admin" // 임시
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
//                System.out.println("[JWT Filter] Claims: " + claims);
            } catch (ExpiredJwtException e) {
//                System.out.println("[JWT Filter] Token expired: " + e.getMessage());
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            } catch (Exception e) {
//                System.out.println("[JWT Filter] Token invalid: " + e.getMessage());
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            Authentication authentication = jwtProvider.getAuthentication(claims);
//            System.out.println("[JWT Filter] Authentication Principal: " + authentication.getPrincipal());

            if(authentication == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
