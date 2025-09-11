package com.project.give.dto.account.request;

import com.project.give.entity.User;
import lombok.Data;

@Data
public class UserSignupRequestDto {
    private String username; // 회원 아이디, 검증 패턴 적용 예정
    private String email;    // 이메일, 검증 패턴 적용 예정
    private String password; // 비밀번호, 검증 패턴 적용 예정
    private String name;     // 이름, 검증 패턴 적용 예정

    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password) // 인코딩 예정
                .name(name)
                .build();
    }
}
