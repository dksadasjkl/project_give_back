package com.project.give.dto.dashboard;

import lombok.Data;

@Data
public class TopDonationProjectDto {
    private int donationProjectId;
    private String donationProjectTitle;
    private String donationProjectOrganization;
    private String donationProjectImageUrl;
    private String donationProjectStartDate;
}
