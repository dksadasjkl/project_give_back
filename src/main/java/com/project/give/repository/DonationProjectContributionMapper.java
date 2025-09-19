package com.project.give.repository;

import com.project.give.entity.DonationProjectContribution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationProjectContributionMapper {
    public int insertContribution(DonationProjectContribution donationProjectContribution);
    public List<DonationProjectContribution> selectContributionsByProjectId(@Param("donationProjectId") int donationProjectId);
    public int deleteContribution(@Param("donationProjectContributionId") int donationProjectContributionId);
}
