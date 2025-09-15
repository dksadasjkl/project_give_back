package com.project.give.dto.account.request;

import com.project.give.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class UserSignupRequestDto {
    private String username; // 회원 아이디, 검증 패턴 적용 예정
    private String email;    // 이메일, 검증 패턴 적용 예정
    private String password; // 비밀번호, 검증 패턴 적용 예정
    private String name;     // 이름, 검증 패턴 적용 예정
    private String nickname;
    private String phone;

    // 비밀번호는 BCryptPasswordEncoder를 사용하여 암호화
    public User toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .username(username)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password)) // 비밀번호 암호화
                .name(name)
                .nickname(nickname)
                .phone(phone)
                .build();
    }
}
