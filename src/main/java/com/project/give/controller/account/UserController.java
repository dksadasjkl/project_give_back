package com.project.give.controller.account;

import com.project.give.aop.annotation.ValidAspect;
import com.project.give.dto.account.request.OAuth2SignupRequestDto;
import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @ValidAspect
    @PostMapping("/oauth2/sign-up")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupRequestDto oAuth2SignupRequestDto, BindingResult bindingResult) {
        userService.oAuth2Signup(oAuth2SignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipalUser() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }


}