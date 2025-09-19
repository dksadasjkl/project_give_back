package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProjectContribution;
import lombok.Data;

@Data
public class PostDonationProjectContributionRequestDto {
    private int donationProjectId;
    private int userId;
    private int donationProjectContributionAmount;

    public DonationProjectContribution toEntity(String nickname) {
        return DonationProjectContribution.builder()
                .donationProjectId(donationProjectId)
                .userId(userId)
                .donationProjectContributorName(nickname)
                .donationProjectContributionAmount(donationProjectContributionAmount)
                .build();
    }

}
