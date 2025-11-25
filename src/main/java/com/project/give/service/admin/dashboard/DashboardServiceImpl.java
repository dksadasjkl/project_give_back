package com.project.give.service.admin.dashboard;

import com.project.give.dto.dashboard.DashboardResponseDto;
import com.project.give.repository.DashboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public DashboardResponseDto getDashboardData() {
        DashboardResponseDto dto = new DashboardResponseDto();

        dto.setDonationProjectCount(dashboardMapper.countDonationProjects());
        dto.setFundingProjectCount(dashboardMapper.countFundingProjects());
        dto.setTotalDonationAmount(dashboardMapper.sumTotalDonationAmount());
        dto.setTodayDonationAmount(dashboardMapper.sumTodayDonationAmount());
        dto.setMonthlyDonationAmount(dashboardMapper.sumMonthlyDonationAmount());

        dto.setStoreProductCount(dashboardMapper.countStoreProducts());
        dto.setStoreOrderCount(dashboardMapper.countStoreOrders());
        dto.setTotalSales(dashboardMapper.sumTotalSales());
        dto.setTodayOrderCount(dashboardMapper.countTodayOrders());
        dto.setMonthlySales(dashboardMapper.sumMonthlySales());

        dto.setUserCount(dashboardMapper.countUsers());
        dto.setTodayUserCount(dashboardMapper.countTodayUsers());

        dto.setDonationDailyStats(dashboardMapper.getDonationDailyStats());
        dto.setSalesDailyStats(dashboardMapper.getSalesDailyStats());

        dto.setDonationMonthlyStats(dashboardMapper.getDonationMonthlyStats());
        dto.setSalesMonthlyStats(dashboardMapper.getSalesMonthlyStats());

        dto.setRecentDonations(dashboardMapper.getRecentDonations());

        dto.setTopDonationProjects(dashboardMapper.selectTopDonationProjects());
        dto.setTopStoreProducts(dashboardMapper.selectTopStoreProducts());

        return dto;
    }
}
