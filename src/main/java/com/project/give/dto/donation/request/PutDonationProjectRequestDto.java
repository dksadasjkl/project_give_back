package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProject;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PutDonationProjectRequestDto {
    private String donationProjectTitle;
    private String donationProjectOrganization;
    private String donationProjectImageUrl;
    private int donationCategoryId;
    private int donationProjectCurrentAmount;
    private int donationProjectTargetAmount;
    private LocalDate donationProjectStartDate;
    private LocalDate donationProjectEndDate;

    public DonationProject toEntity(int donationProjectId) {
        return DonationProject.builder()
                .donationProjectId(donationProjectId)
                .donationProjectTitle(donationProjectTitle)
                .donationProjectOrganization(donationProjectOrganization)
                .donationProjectImageUrl(donationProjectImageUrl)
                .donationCategoryId(donationCategoryId)
                .donationProjectCurrentAmount(donationProjectCurrentAmount)
                .donationProjectTargetAmount(donationProjectTargetAmount)
                .donationProjectStartDate(donationProjectStartDate)
                .donationProjectEndDate(donationProjectEndDate)
                .build();
    }
}
