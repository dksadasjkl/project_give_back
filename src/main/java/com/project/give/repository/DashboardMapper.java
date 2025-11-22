package com.project.give.repository;

import com.project.give.dto.dashboard.DailyStatDto;
import com.project.give.dto.dashboard.RecentDonationDto;
import com.project.give.dto.dashboard.TopDonationProjectDto;
import com.project.give.dto.dashboard.TopStoreProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {

    int countDonationProjects();
    int countFundingProjects();
    int sumTotalDonationAmount();
    int sumTodayDonationAmount();
    int sumMonthlyDonationAmount();

    int countStoreProducts();
    int countStoreOrders();
    int sumTotalSales();
    int countTodayOrders();
    int sumMonthlySales();

    int countUsers();
    int countTodayUsers();

    List<DailyStatDto> getDonationDailyStats();
    List<DailyStatDto> getSalesDailyStats();
    List<RecentDonationDto> getRecentDonations();

    List<Map<String, Object>> selectTopDonationProjects();
    List<Map<String, Object>> selectTopStoreProducts();
}
