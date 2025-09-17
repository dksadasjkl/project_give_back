package com.project.give.dto.donation.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetDonationCategoryResponseDto {
    private int donationCategoryId;
    private String donationCategoryNameKor;
}
