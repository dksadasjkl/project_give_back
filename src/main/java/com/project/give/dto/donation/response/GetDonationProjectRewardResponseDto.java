package com.project.give.dto.donation.response;

import com.project.give.entity.DonationProjectReward;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetDonationProjectRewardResponseDto {
    private int donationProjectRewardId;
    private int donationProjectId;
    private String donationProjectRewardTitle;
    private String donationProjectRewardDescription;
    private int donationProjectRewardPrice;
    private int donationProjectRewardQuantity;
    private int donationProjectRewardRemaining;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static GetDonationProjectRewardResponseDto fromEntity(DonationProjectReward donationProjectReward) {
        return GetDonationProjectRewardResponseDto.builder()
                .donationProjectRewardId(donationProjectReward.getDonationProjectRewardId())
                .donationProjectId(donationProjectReward.getDonationProjectId())
                .donationProjectRewardTitle(donationProjectReward.getDonationProjectRewardTitle())
                .donationProjectRewardDescription(donationProjectReward.getDonationProjectRewardDescription())
                .donationProjectRewardPrice(donationProjectReward.getDonationProjectRewardPrice())
                .donationProjectRewardQuantity(donationProjectReward.getDonationProjectRewardQuantity())
                .donationProjectRewardRemaining(donationProjectReward.getDonationProjectRewardRemaining())
                .createDate(donationProjectReward.getCreateDate())
                .updateDate(donationProjectReward.getUpdateDate())
                .build();
    }
}
