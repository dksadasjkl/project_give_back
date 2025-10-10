package com.project.give.repository;

import com.project.give.entity.DonationProject;
import com.project.give.entity.DonationProjectContribution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationProjectContributionMapper {
    public int insertContribution(DonationProjectContribution donationProjectContribution);
    public List<DonationProjectContribution> selectContributionsByProjectId(@Param("donationProjectId") int donationProjectId);
    public int deleteContribution(@Param("donationProjectContributionId") int donationProjectContributionId);
    public List<DonationProjectContribution> selectContributionsWithPaging (
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("donationProjectId") int donationProjectId
    );
    public int selectContributionCount(@Param("donationProjectId") int donationProjectId);
}
