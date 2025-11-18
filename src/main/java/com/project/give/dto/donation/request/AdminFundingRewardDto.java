package com.project.give.dto.donation.request;

import lombok.Data;

@Data
public class AdminFundingRewardDto {
    private String rewardName;
    private int rewardAmount;
    private int rewardLimit;
    private String rewardImageUrl;
}
