package com.project.give.controller.account;

import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // 회원 관련 API
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        System.out.println(userSignupRequestDto);
        userService.signup(userSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    };

}