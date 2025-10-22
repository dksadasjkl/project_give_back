package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProjectReward;
import lombok.Data;

@Data
public class PostDonationProjectRewardRequestDto {
    private int donationProjectId;
    private String donationProjectRewardTitle;
    private String donationProjectRewardDescription;
    private int donationProjectRewardPrice;
    private int donationProjectRewardQuantity;
    private int donationProjectRewardRemaining;

    public DonationProjectReward toEntity() {
        return DonationProjectReward.builder()
                .donationProjectId(donationProjectId)
                .donationProjectRewardTitle(donationProjectRewardTitle)
                .donationProjectRewardDescription(donationProjectRewardDescription)
                .donationProjectRewardPrice(donationProjectRewardPrice)
                .donationProjectRewardQuantity(donationProjectRewardQuantity)
                .donationProjectRewardRemaining(donationProjectRewardRemaining)
                .build();
    }
}
