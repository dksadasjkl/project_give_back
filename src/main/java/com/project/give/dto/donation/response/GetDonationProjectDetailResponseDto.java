package com.project.give.dto.donation.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class GetDonationProjectDetailResponseDto {
    private int donationProjectDetailId;
    private int donationProjectId;
    private String donationProjectDetailSubtitle;
    private String donationProjectDetailImageUrl;
    private String donationProjectDetailContent;
    private int donationProjectDetailOrderNo;
    private String donationProjectTitle;
    private String donationProjectOrganization;
    private String donationProjectOrganizationImageUrl;
    private String donationProjectImageUrl;
    private int donationProjectCurrentAmount;
    private int donationProjectTargetAmount;
    private LocalDate donationProjectStartDate;
    private LocalDate donationProjectEndDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
