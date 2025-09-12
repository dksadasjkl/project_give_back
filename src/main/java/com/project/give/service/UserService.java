package com.project.give.service;

import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.entity.User;
import com.project.give.repository.UserMapper;
import com.project.give.exception.DataSaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signup (UserSignupRequestDto userSignupRequestDto) {
        int successCount = 0;
        User user = userSignupRequestDto.toEntity(bCryptPasswordEncoder);
        successCount += userMapper.saveUser(user);

        if (successCount < 1) {
            throw new DataSaveException("회원 저장 실패");
        }
    }
}