package com.project.give.dto.donation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class GetDonationProjectsResponseDto {
    private int donationProjectId;
    private String donationProjectTitle;
    private String donationProjectOrganization;
    private String donationProjectOrganizationImageUrl;
    private String donationProjectImageUrl;
    private int donationCategoryId;
    private int donationProjectCurrentAmount;
    private int donationProjectTargetAmount;
    private LocalDate donationProjectStartDate;
    private LocalDate donationProjectEndDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String donationCategoryNameKor;
    private int totalContribution;
    private LocalDateTime lastContributionDate;
}
