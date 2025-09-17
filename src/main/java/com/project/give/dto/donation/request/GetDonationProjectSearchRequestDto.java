package com.project.give.dto.donation.request;

import lombok.Data;

@Data
public class GetDonationProjectSearchRequestDto {
    private int startIndex;
    private int count;
    private int donationCategoryId;
    private int searchTypeId;
}
