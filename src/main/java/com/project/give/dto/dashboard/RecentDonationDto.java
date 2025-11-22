package com.project.give.dto.dashboard;

import lombok.Data;

@Data
public class RecentDonationDto {
    private String username;
    private int amount;
    private String projectTitle;
    private String date;
}
