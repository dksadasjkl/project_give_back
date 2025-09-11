package com.project.give.service;

import com.project.give.dto.account.request.UserLoginRequestDto;
import com.project.give.entity.User;
import com.project.give.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    public String login(UserLoginRequestDto userLoginRequestDto) {
        User user = userMapper.findUserByUsername(userLoginRequestDto.getUsername());
        
        // 사용자 정보가 틀릴 시 정보 확인 에러 발생 예정

        // 3️⃣ 로그인 성공 시 메시지 반환
        return "로그인 성공";
    }
}
