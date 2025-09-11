package com.project.give.service;

import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.entity.User;
import com.project.give.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void signup (UserSignupRequestDto userSignupRequestDto) {
        int successCount = 0;
        User user = userSignupRequestDto.toEntity();
        System.out.println(user);
        successCount += userMapper.saveUser(user);
    }
}
