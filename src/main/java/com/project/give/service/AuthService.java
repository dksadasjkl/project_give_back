package com.project.give.service;

import com.project.give.dto.account.request.UserLoginRequestDto;
import com.project.give.entity.User;
import com.project.give.jwt.JwtProvider;
import com.project.give.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    public String login(UserLoginRequestDto userLoginRequestDto) {
        User user = userMapper.findUserByUsername(userLoginRequestDto.getUsername());
        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요");
        }
        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요");
        }
        // 로그인 성공 시 토근 반환
        return jwtProvider.generateToken(user);
    }
}
