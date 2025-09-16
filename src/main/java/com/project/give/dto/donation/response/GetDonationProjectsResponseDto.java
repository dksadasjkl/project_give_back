package com.project.give.dto.donation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDonationProjectsResponseDto {
    private int donationProjectId;
    private String donationProjectTitle;
    private String donationProjectOrganization;
    private String donationProjectImageUrl;
    private int donationCategoryId;
    private int donationProjectCurrentAmount;
    private int donationProjectTargetAmount;
    private LocalDate donationProjectStartDate;
    private LocalDate donationProjectEndDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
