package com.project.give.dto.account.request;

import lombok.Data;

@Data
public class UserPasswordRequestDto {
    private String oldPassword;
    private String newPassword;
    private String newPasswordCheck;
}
