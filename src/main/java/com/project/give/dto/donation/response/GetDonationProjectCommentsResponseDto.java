package com.project.give.dto.donation.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetDonationProjectCommentsResponseDto {
    private int donationProjectCommentId;
    private int donationProjectId;
    private int userId;
    private String donationProjectCommentText;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private String username;
    private String nickname;

    private String donationProjectTitle;
    private String donationProjectImageUrl;

}
