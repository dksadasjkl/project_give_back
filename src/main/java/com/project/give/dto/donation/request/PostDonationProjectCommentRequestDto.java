package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProjectComment;
import lombok.Data;

@Data
public class PostDonationProjectCommentRequestDto {
    private int donationProjectId;
    private int userId;
    private String donationProjectCommentText;

    public DonationProjectComment toEntity() {
        return DonationProjectComment.builder()
                .donationProjectId(donationProjectId)
                .userId(userId)
                .donationProjectCommentText(donationProjectCommentText)
                .build();
    }
}