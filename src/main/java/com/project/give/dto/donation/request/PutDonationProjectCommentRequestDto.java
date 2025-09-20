package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProjectComment;
import com.project.give.entity.DonationProjectDetail;
import lombok.Data;

@Data
public class PutDonationProjectCommentRequestDto {
    private String donationProjectCommentText;

    public DonationProjectComment toEntity(int donationProjectCommentId) {
        return DonationProjectComment.builder()
                .donationProjectCommentId(donationProjectCommentId)
                .donationProjectCommentText(donationProjectCommentText)
                .build();
    }
}
