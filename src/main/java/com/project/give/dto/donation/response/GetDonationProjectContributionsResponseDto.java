package com.project.give.dto.donation.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetDonationProjectContributionsResponseDto {
    private int donationProjectContributionId;
    private int donationProjectId;
    private int userId;
    private String donationProjectContributorName;
    private int donationProjectContributionAmount;
    private LocalDateTime donationProjectContributionDate;
}
