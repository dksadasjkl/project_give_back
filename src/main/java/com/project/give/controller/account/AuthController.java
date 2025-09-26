package com.project.give.controller.account;

import com.project.give.dto.account.request.UserLoginRequestDto;
import com.project.give.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // 인증 관련 API
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        try {
            String token = authService.login(userLoginRequestDto);
            return ResponseEntity.ok(token); // 로그인 성공 → 200 OK + 토큰
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
//
//    @GetMapping("/login/status")
//    public ResponseEntity<?> getLoginStatus(@RequestHeader("Authorization") String authorization) {
//        System.out.println(authorization);
//        return ResponseEntity.ok(jwtService.validLoginAccessToken(authorization));
//    }
}
