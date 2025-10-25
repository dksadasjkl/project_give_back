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
    private int fundingProjectRewardId;       // PK
    private int donationProjectId;            // FK
    private String fundingProjectRewardTitle;
    private String fundingProjectRewardDescription;
    private String fundingProjectRewardDetail;   // 추가
    private int fundingProjectRewardPrice;
    private int fundingProjectRewardQuantity;
    private int fundingProjectRewardRemaining;
    private String fundingProjectRewardDeliveryInfo;
    private String fundingProjectRewardNotice;
    private String fundingProjectRewardImageUrl; // 추가
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}