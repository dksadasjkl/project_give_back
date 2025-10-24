package com.project.give.dto.donation.request;

import com.project.give.entity.FundingProjectReward;
import lombok.Data;

@Data
public class PutFundingProjectRewardRequestDto {
    private int fundingProjectRewardId;
    private int donationProjectId;
    private String fundingProjectRewardTitle;
    private String fundingProjectRewardDescription;
    private int fundingProjectRewardPrice;
    private int fundingProjectRewardQuantity;
    private int fundingProjectRewardRemaining;
    private String fundingProjectRewardDeliveryInfo;
    private String fundingProjectRewardNotice;

    public FundingProjectReward toEntity() {
        return FundingProjectReward.builder()
                .fundingProjectRewardId(fundingProjectRewardId)
                .donationProjectId(donationProjectId)
                .fundingProjectRewardTitle(fundingProjectRewardTitle)
                .fundingProjectRewardDescription(fundingProjectRewardDescription)
                .fundingProjectRewardPrice(fundingProjectRewardPrice)
                .fundingProjectRewardQuantity(fundingProjectRewardQuantity)
                .fundingProjectRewardRemaining(fundingProjectRewardRemaining)
                .fundingProjectRewardDeliveryInfo(fundingProjectRewardDeliveryInfo)
                .fundingProjectRewardNotice(fundingProjectRewardNotice)
                .build();
    }
}
