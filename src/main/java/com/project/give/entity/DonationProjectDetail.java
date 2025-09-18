package com.project.give.entity;

import com.project.give.dto.donation.response.GetDonationProjectDetailResponseDto;
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
public class DonationProjectDetail {
    private int donationProjectDetailId;
    private int donationProjectId;
    private String donationProjectDetailSubtitle;
    private String donationProjectDetailImageUrl;
    private String donationProjectDetailContent;
    private int donationProjectDetailOrderNo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private DonationProject donationProject;


    public GetDonationProjectDetailResponseDto toGetDonationProjectDetailResponseDto() {
        return GetDonationProjectDetailResponseDto.builder()
                // Detail 정보
                .donationProjectDetailId(donationProjectDetailId)
                .donationProjectId(donationProjectId)
                .donationProjectDetailSubtitle(donationProjectDetailSubtitle)
                .donationProjectDetailImageUrl(donationProjectDetailImageUrl)
                .donationProjectDetailContent(donationProjectDetailContent)
                .donationProjectDetailOrderNo(donationProjectDetailOrderNo)

                // Project 정보
                .donationProjectTitle(donationProject.getDonationProjectTitle())
                .donationProjectOrganization(donationProject.getDonationProjectOrganization())
                .donationProjectOrganizationImageUrl(donationProject.getDonationProjectOrganizationImageUrl())
                .donationProjectImageUrl(donationProject.getDonationProjectImageUrl())
                .donationProjectCurrentAmount(donationProject.getDonationProjectCurrentAmount())
                .donationProjectTargetAmount(donationProject.getDonationProjectTargetAmount())
                .donationProjectStartDate(donationProject.getDonationProjectStartDate())
                .donationProjectEndDate(donationProject.getDonationProjectEndDate())
                .createDate(donationProject.getCreateDate())
                .updateDate(donationProject.getUpdateDate())
                .build();
    }
}
