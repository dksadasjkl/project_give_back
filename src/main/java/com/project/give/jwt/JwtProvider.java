package com.project.give.jwt;

import com.project.give.entity.PrincipalUser;
import com.project.give.entity.User;
import com.project.give.repository.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Collection;
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
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // 임시로 토근 만료시간 30일로 지정 -> Access Token 1시간으로 변경 예정
        Date expireDate = new Date(new Date().getTime() + (1000l * 60l * 60l * 24l * 30l));

        String accessToken = Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("username", user.getUsername())
                .claim("password", user.getPassword())
                .claim("authorities", authorities)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    public String removeBearer(String token) {
        if (token == null) return null;
        token = token.trim(); // 앞뒤 공백 제거
        if (token.startsWith("Bearer ")) {
            return token.substring(7).trim(); // "Bearer " 제거 + 남은 공백 제거
        }
        return null;
    }

    public Claims getClaims(String token) {
        Claims claims =  Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public Authentication getAuthentication (Claims claims) {
        String username = claims.get("username").toString();
        User user = userMapper.findUserByUsername(username);
        if(user == null) {
            return null;
        }
        PrincipalUser principalUser = user.toPrincipalUser();
        return new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
    }
}
