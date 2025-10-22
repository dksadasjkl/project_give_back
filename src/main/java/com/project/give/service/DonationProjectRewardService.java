package com.project.give.service;

import com.project.give.dto.donation.request.PostDonationProjectRewardRequestDto;
import com.project.give.dto.donation.response.GetDonationProjectRewardResponseDto;
import com.project.give.entity.DonationProjectReward;
import com.project.give.repository.DonationProjectRewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationProjectRewardService {

    @Autowired
    private DonationProjectRewardMapper donationProjectRewardMapper;

    public void createDonationProjectReward(PostDonationProjectRewardRequestDto dto) {
        DonationProjectReward reward = dto.toEntity();
        donationProjectRewardMapper.insertDonationProjectReward(reward);
    }

    public List<GetDonationProjectRewardResponseDto> getDonationProjectRewards(int donationProjectId) {
        return donationProjectRewardMapper.selectDonationProjectRewards(donationProjectId)
                .stream()
                .map(GetDonationProjectRewardResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void updateDonationProjectReward(DonationProjectReward reward) {
        donationProjectRewardMapper.updateDonationProjectReward(reward);
    }

    public void deleteDonationProjectReward(int rewardId) {
        donationProjectRewardMapper.deleteDonationProjectReward(rewardId);
    }
}
