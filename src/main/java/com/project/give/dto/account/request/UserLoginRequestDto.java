package com.project.give.dto.account.request;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String username;
    private String password;
}
