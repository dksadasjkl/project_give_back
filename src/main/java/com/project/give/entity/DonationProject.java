package com.project.give.entity;

import com.project.give.dto.donation.response.GetDonationProjectsResponseDto;
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
public class DonationProject {
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

    private DonationCategory donationCategory;

    // 기본 이미지 적용 getter 이미지는 나중에 수정
    public String getDonationProjectOrganizationImageUrl() {
        if (donationProjectOrganizationImageUrl == null || donationProjectOrganizationImageUrl.isEmpty()) {
            return "https://example.com/default-image.png";
        }
        return donationProjectOrganizationImageUrl;
    }

    public GetDonationProjectsResponseDto toGetDonationProjectsResponseDto () {
        return GetDonationProjectsResponseDto.builder()
                .donationProjectId(donationProjectId)
                .donationProjectTitle(donationProjectTitle)
                .donationProjectOrganization(donationProjectOrganization)
                .donationProjectOrganizationImageUrl(getDonationProjectOrganizationImageUrl())
                .donationProjectImageUrl(donationProjectImageUrl)
                .donationCategoryId(donationCategoryId)
                .donationProjectCurrentAmount(donationProjectCurrentAmount)
                .donationProjectTargetAmount(donationProjectTargetAmount)
                .donationProjectStartDate(donationProjectStartDate)
                .donationProjectEndDate(donationProjectEndDate)
                .createDate(createDate)
                .updateDate(updateDate)
                .donationCategoryNameKor(donationCategory.getDonationCategoryNameKor())
                .build();
    }
}
