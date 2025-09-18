package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProjectDetail;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDonationProjectDetailRequestDto {
    private int donationProjectId;
    private String donationProjectDetailSubtitle;
    private String donationProjectDetailImageUrl;
    private String donationProjectDetailContent;
    private int donationProjectDetailOrderNo;

    public DonationProjectDetail toEntity() {
        return DonationProjectDetail.builder()
                .donationProjectId(donationProjectId)
                .donationProjectDetailSubtitle(donationProjectDetailSubtitle)
                .donationProjectDetailImageUrl(donationProjectDetailImageUrl)
                .donationProjectDetailContent(donationProjectDetailContent)
                .donationProjectDetailOrderNo(donationProjectDetailOrderNo)
                .build();
    }
}
