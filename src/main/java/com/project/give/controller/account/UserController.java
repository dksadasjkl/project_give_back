package com.project.give.controller.account;

import com.project.give.aop.annotation.ValidAspect;
import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users") // 회원 관련 API
public class UserController {

    @Autowired
    private UserService userService;

    @ValidAspect
    @PostMapping
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto, BindingResult bindingResult) {
        userService.signup(userSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    };



}