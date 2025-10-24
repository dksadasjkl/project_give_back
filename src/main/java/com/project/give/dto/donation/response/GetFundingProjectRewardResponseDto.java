package com.project.give.dto.donation.response;

import com.project.give.entity.FundingProjectReward;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class GetFundingProjectRewardResponseDto {
    private int fundingProjectRewardId;
    private int donationProjectId;
    private String fundingProjectRewardTitle;
    private String fundingProjectRewardDescription;
    private int fundingProjectRewardPrice;
    private int fundingProjectRewardQuantity;
    private int fundingProjectRewardRemaining;
    private String fundingProjectRewardDeliveryInfo;
    private LocalDate fundingProjectRewardPaymentDate;
    private LocalDate fundingProjectRewardShippingDate;
    private String fundingProjectRewardNotice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static GetFundingProjectRewardResponseDto fromEntity(FundingProjectReward reward) {
        return GetFundingProjectRewardResponseDto.builder()
                .fundingProjectRewardId(reward.getFundingProjectRewardId())
                .donationProjectId(reward.getDonationProjectId())
                .fundingProjectRewardTitle(reward.getFundingProjectRewardTitle())
                .fundingProjectRewardDescription(reward.getFundingProjectRewardDescription())
                .fundingProjectRewardPrice(reward.getFundingProjectRewardPrice())
                .fundingProjectRewardQuantity(reward.getFundingProjectRewardQuantity())
                .fundingProjectRewardRemaining(reward.getFundingProjectRewardRemaining())
                .fundingProjectRewardDeliveryInfo(reward.getFundingProjectRewardDeliveryInfo())
                .fundingProjectRewardNotice(reward.getFundingProjectRewardNotice())
                .createDate(reward.getCreateDate())
                .updateDate(reward.getUpdateDate())
                .build();
    }
}
