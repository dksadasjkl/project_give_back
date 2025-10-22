package com.project.give.repository;

import com.project.give.entity.DonationProjectReward;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationProjectRewardMapper {
    public int insertDonationProjectReward(DonationProjectReward reward);

    public List<DonationProjectReward> selectDonationProjectRewards(@Param("donationProjectId") int donationProjectId);

    public int updateDonationProjectReward(DonationProjectReward reward);

    public int deleteDonationProjectReward(@Param("donationProjectRewardId") int donationProjectRewardId);
}
