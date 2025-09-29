package com.project.give.dto.account.request;

import lombok.Data;

@Data
public class PasswordResetRequestDto {
    private String username;
    private String email;
}
