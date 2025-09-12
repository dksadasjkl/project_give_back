package com.project.give.jwt;

import com.project.give.entity.User;
import com.project.give.repository.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;
    private UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret, UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;

    }

    public String generateToken(User user) {
        // 권한 설정 예정

        // 임시로 토근 만료시간 30일로 지정 -> Access Token 1시간으로 변경 예정
        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 30));

        String accessToken = Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("username", user.getUsername())
                // 권한 추가 예정
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    public String removeBearer(String token) {
        if(!StringUtils.hasText(token)) {
            return null;
        }
        return token.substring("Bearer ".length());
    }

    public Claims getClaims(String token) {
        Claims claims =  Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
