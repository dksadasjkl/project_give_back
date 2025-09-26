package com.project.give.controller.account;

import com.project.give.aop.annotation.ValidAspect;
import com.project.give.dto.account.request.OAuth2SignupRequestDto;
import com.project.give.dto.account.request.PasswordResetRequestDto;
import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.service.AccountService;
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
    @Autowired
    private AccountService accountService;

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


    @PutMapping("/passwordReset")
    public ResponseEntity<?> updateUser(@RequestBody PasswordResetRequestDto passwordResetRequestDto){
        accountService.resetPassword(passwordResetRequestDto);
        boolean result = accountService.resetPassword(passwordResetRequestDto);
        if(result) {
            return ResponseEntity.ok("비밀번호 초기화 완료");
        } else {
            return ResponseEntity.badRequest().body("해당 이메일을 찾을 수 없습니다.");
        }
    }

}