package com.project.give.dto.dashboard;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class DashboardResponseDto {

    private int donationProjectCount;
    private int fundingProjectCount;
    private int totalDonationAmount;
    private int todayDonationAmount;
    private int monthlyDonationAmount;

    private int storeProductCount;
    private int storeOrderCount;
    private int totalSales;
    private int todayOrderCount;
    private int monthlySales;

    private int userCount;
    private int todayUserCount;

    private List<DailyStatDto> donationDailyStats;
    private List<DailyStatDto> salesDailyStats;

    private List<DailyStatDto> donationMonthlyStats;
    private List<DailyStatDto> salesMonthlyStats;

    private List<RecentDonationDto> recentDonations;

    private List<Map    <String, Object>> topDonationProjects;
    private List<Map<String, Object>> topStoreProducts;
}