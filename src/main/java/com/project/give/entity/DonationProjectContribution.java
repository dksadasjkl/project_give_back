package com.project.give.entity;

import com.project.give.dto.donation.response.GetDonationProjectContributionsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationProjectContribution {
    private int donationProjectContributionId;
    private int donationProjectId;
    private int userId;
    private String donationProjectContributorName;
    private int donationProjectContributionAmount;
    private LocalDateTime donationProjectContributionDate;

    public GetDonationProjectContributionsResponseDto toGetDonationProjectContributionsResponseDto() {
        return GetDonationProjectContributionsResponseDto.builder()
                .donationProjectContributionId(donationProjectContributionId)
                .donationProjectId(donationProjectId)
                .userId(userId)
                .donationProjectContributorName(donationProjectContributorName)
                .donationProjectContributionAmount(donationProjectContributionAmount)
                .donationProjectContributionDate(donationProjectContributionDate)
                .build();
    }
}
