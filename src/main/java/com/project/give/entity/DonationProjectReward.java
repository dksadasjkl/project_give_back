package com.project.give.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationProjectReward {
    private int donationProjectRewardId;
    private int donationProjectId;
    private String donationProjectRewardTitle;
    private String donationProjectRewardDescription;
    private int donationProjectRewardPrice;
    private int donationProjectRewardQuantity;
    private int donationProjectRewardRemaining;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
