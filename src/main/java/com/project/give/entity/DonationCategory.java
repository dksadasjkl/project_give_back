package com.project.give.entity;

import com.project.give.dto.donation.response.GetDonationCategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationCategory {
    private int donationCategoryId;
    private String donationCategoryNameKor;

    public GetDonationCategoryResponseDto toGetDonationCategorysResponseDto () {
        return GetDonationCategoryResponseDto.builder()
                .donationCategoryId(donationCategoryId)
                .donationCategoryNameKor(donationCategoryNameKor)
                .build();
    }
}
