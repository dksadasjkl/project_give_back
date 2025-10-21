package com.project.give.entity;

import com.project.give.dto.donation.response.GetDonationProjectCommentsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationProjectComment {
    private int donationProjectCommentId;
    private int donationProjectId;
    private int userId;
    private String donationProjectCommentText;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private User user;

    private String donationProjectTitle;
    private String donationProjectImageUrl;

    public GetDonationProjectCommentsResponseDto toGetDonationProjectCommentsResponseDto() {
        return GetDonationProjectCommentsResponseDto.builder()
                .donationProjectCommentId(donationProjectCommentId)
                .donationProjectId(donationProjectId)
                .userId(userId)
                .donationProjectCommentText(donationProjectCommentText)
                .createDate(createDate)
                .updateDate(updateDate)
                .username(user.getUsername())
                .nickname(user.getNickname())
                .donationProjectTitle(donationProjectTitle)
                .donationProjectImageUrl(donationProjectImageUrl)
                .build();
    }
}
