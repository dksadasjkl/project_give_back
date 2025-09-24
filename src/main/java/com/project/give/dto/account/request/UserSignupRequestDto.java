package com.project.give.dto.account.request;

import com.project.give.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class UserSignupRequestDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 5 ~ 10자리 형식이어야 합니다")
    private String username;
    @Email(regexp =  "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{1,3}$", message = "이메일 형식이어야 합니다")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "하나의 영문자, 숫자, 특수문자를 포함한 5 ~ 128자리 형식이어야 합니다")
    private String password;
    @Pattern(regexp = "^[가-힣]{1,}$", message = "한글문자 형식이어야 합니다")
    private String name;
    @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글 형식이어야 합니다.")
    private String nickname;
    @Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})+$",  message = "ex) 01012345678 형식이어야 합니다")
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
