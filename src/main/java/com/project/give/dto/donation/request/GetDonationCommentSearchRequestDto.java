package com.project.give.dto.donation.request;

import lombok.Data;

@Data
public class GetDonationCommentSearchRequestDto {
    private int startIndex;
    private int count;
    private int donationProjectId;
}