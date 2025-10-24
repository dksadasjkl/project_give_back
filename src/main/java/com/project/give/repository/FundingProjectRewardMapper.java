package com.project.give.repository;

import com.project.give.entity.FundingProjectReward;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FundingProjectRewardMapper {
    public int insertFundingProjectReward(FundingProjectReward reward);

    public List<FundingProjectReward> selectFundingProjectRewards(@Param("donationProjectId") int donationProjectId);

    public int updateFundingProjectReward(FundingProjectReward reward);

    public int deleteFundingProjectReward(@Param("fundingProjectRewardId") int fundingProjectRewardId);
}
