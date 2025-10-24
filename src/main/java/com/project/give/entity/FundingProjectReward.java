package com.project.give.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundingProjectReward {
    private int fundingProjectRewardId;
    private int donationProjectId;
    private String fundingProjectRewardTitle;
    private String fundingProjectRewardDescription;
    private int fundingProjectRewardPrice;
    private int fundingProjectRewardQuantity;
    private int fundingProjectRewardRemaining;
    private String fundingProjectRewardDeliveryInfo;
    private String fundingProjectRewardNotice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}