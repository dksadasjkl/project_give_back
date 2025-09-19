package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProjectDetail;
import lombok.Data;

@Data
public class PutDonationProjectDetailRequestDto {
    private String donationProjectDetailSubtitle;
    private String donationProjectDetailImageUrl;
    private String donationProjectDetailContent;
    private int donationProjectDetailOrderNo;

    public DonationProjectDetail toEntity(int donationProjectDetailId) {
        return DonationProjectDetail.builder()
                .donationProjectDetailId(donationProjectDetailId)
                .donationProjectDetailSubtitle(donationProjectDetailSubtitle)
                .donationProjectDetailImageUrl(donationProjectDetailImageUrl)
                .donationProjectDetailContent(donationProjectDetailContent)
                .donationProjectDetailOrderNo(donationProjectDetailOrderNo)
                .build();
    }
}
