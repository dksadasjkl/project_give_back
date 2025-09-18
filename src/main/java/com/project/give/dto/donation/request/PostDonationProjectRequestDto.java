package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProject;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDonationProjectRequestDto {
    private String donationProjectTitle;
    private String donationProjectOrganization;
    private String donationProjectOrganizationImageUrl;
    private String donationProjectImageUrl;
    private int donationCategoryId;
    private int donationProjectCurrentAmount;
    private int donationProjectTargetAmount;

    public DonationProject toEntity() {
        return DonationProject.builder()
                .donationProjectTitle(donationProjectTitle)
                .donationProjectOrganization(donationProjectOrganization)
                .donationProjectOrganizationImageUrl(donationProjectOrganizationImageUrl)
                .donationProjectImageUrl(donationProjectImageUrl)
                .donationCategoryId(donationCategoryId)
                .donationProjectCurrentAmount(donationProjectCurrentAmount)
                .donationProjectTargetAmount(donationProjectTargetAmount)
                .build();
    }
}
