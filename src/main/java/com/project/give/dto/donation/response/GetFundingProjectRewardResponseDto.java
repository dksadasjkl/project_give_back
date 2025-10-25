package com.project.give.dto.donation.response;

import com.project.give.entity.FundingProjectReward;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetFundingProjectRewardResponseDto {
    private int fundingProjectRewardId;
    private int donationProjectId;
    private String fundingProjectRewardTitle;
    private String fundingProjectRewardDescription;
    private String fundingProjectRewardDetail;       // 추가
    private int fundingProjectRewardPrice;
    private int fundingProjectRewardQuantity;
    private int fundingProjectRewardRemaining;
    private String fundingProjectRewardDeliveryInfo;
    private String fundingProjectRewardNotice;
    private String fundingProjectRewardImageUrl;     // 추가
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static GetFundingProjectRewardResponseDto fromEntity(FundingProjectReward reward) {
        return GetFundingProjectRewardResponseDto.builder()
                .fundingProjectRewardId(reward.getFundingProjectRewardId())
                .donationProjectId(reward.getDonationProjectId())
                .fundingProjectRewardTitle(reward.getFundingProjectRewardTitle())
                .fundingProjectRewardDescription(reward.getFundingProjectRewardDescription())
                .fundingProjectRewardDetail(reward.getFundingProjectRewardDetail())   // 추가
                .fundingProjectRewardPrice(reward.getFundingProjectRewardPrice())
                .fundingProjectRewardQuantity(reward.getFundingProjectRewardQuantity())
                .fundingProjectRewardRemaining(reward.getFundingProjectRewardRemaining())
                .fundingProjectRewardDeliveryInfo(reward.getFundingProjectRewardDeliveryInfo())
                .fundingProjectRewardNotice(reward.getFundingProjectRewardNotice())
                .fundingProjectRewardImageUrl(reward.getFundingProjectRewardImageUrl()) // 추가
                .createDate(reward.getCreateDate())
                .updateDate(reward.getUpdateDate())
                .build();
    }
}