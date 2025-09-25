package com.project.give.service;

import com.project.give.dto.account.request.OAuth2SignupRequestDto;
import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.entity.User;
import com.project.give.repository.UserMapper;
import com.project.give.exception.DataSaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void signup (UserSignupRequestDto userSignupRequestDto) {
        int successCount = 0;
        User user = userSignupRequestDto.toEntity(bCryptPasswordEncoder);
        successCount += userMapper.saveUser(user);
        successCount += userMapper.saveRole(user.getUserId(), 1); // 임시사용자 부여

        if (successCount < 2) {
            throw new DataSaveException("회원 저장 실패");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void oAuth2Signup(OAuth2SignupRequestDto oAuth2SignupRequestDto) {
        int successCount = 0;
        User user = oAuth2SignupRequestDto.toEntity(bCryptPasswordEncoder);
        successCount += userMapper.saveUser(user);
        successCount += userMapper.saveRole(user.getUserId(), 1);
        successCount += userMapper.saveOAuth2(oAuth2SignupRequestDto.toOAuth2(user.getUserId()));

        if(successCount < 3) {
            throw new RuntimeException("데이터 저장 오류");
        }
    }

}