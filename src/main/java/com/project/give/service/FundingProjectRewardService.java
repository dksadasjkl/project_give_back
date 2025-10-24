package com.project.give.service;

import com.project.give.dto.donation.request.PostFundingProjectRewardRequestDto;
import com.project.give.dto.donation.response.GetFundingProjectRewardResponseDto;
import com.project.give.entity.FundingProjectReward;
import com.project.give.repository.FundingProjectRewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundingProjectRewardService {

    @Autowired
    private FundingProjectRewardMapper donationProjectRewardMapper;

    public void createDonationProjectReward(PostFundingProjectRewardRequestDto dto) {
        FundingProjectReward reward = dto.toEntity();
        donationProjectRewardMapper.insertFundingProjectReward(reward);
    }

    public List<GetFundingProjectRewardResponseDto> getDonationProjectRewards(int donationProjectId) {
        return donationProjectRewardMapper.selectFundingProjectRewards(donationProjectId)
                .stream()
                .map(GetFundingProjectRewardResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void updateDonationProjectReward(FundingProjectReward reward) {
        donationProjectRewardMapper.updateFundingProjectReward(reward);
    }

    public void deleteDonationProjectReward(int fundingProjectRewardId) {
        donationProjectRewardMapper.deleteFundingProjectReward(fundingProjectRewardId);
    }
}
