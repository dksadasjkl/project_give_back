package com.project.give.dto.account.request;

import lombok.Data;

@Data
public class ProfileUpdateRequestDto {
    private String nickname;
    private String profileImageUrl;
}
