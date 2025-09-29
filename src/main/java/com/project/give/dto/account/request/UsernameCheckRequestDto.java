package com.project.give.dto.account.request;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UsernameCheckRequestDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 4 ~ 10자리 형식이어야 합니다")
    private String username;
}
