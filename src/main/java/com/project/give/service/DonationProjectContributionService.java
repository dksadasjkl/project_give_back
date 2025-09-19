package com.project.give.service;

import com.project.give.dto.donation.request.PostDonationProjectContributionRequestDto;
import com.project.give.dto.donation.response.GetDonationProjectContributionsResponseDto;
import com.project.give.entity.DonationProjectContribution;
import com.project.give.entity.User;
import com.project.give.exception.DataNotFoundException;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.DonationProjectContributionMapper;
import com.project.give.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationProjectContributionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DonationProjectContributionMapper donationProjectContributionMapper;

    public void createDonationProjectContribution(PostDonationProjectContributionRequestDto postDonationProjectContributionRequestDto) {
        System.out.println(postDonationProjectContributionRequestDto.getUserId());
        User user = userMapper.findNicknameByUserId(postDonationProjectContributionRequestDto.getUserId());
        System.out.println(user);
        System.out.println(user.getNickname());
        String nickname = user.getNickname();
        if (nickname == null) {
            throw new DataNotFoundException("사용자 정보가 없습니다.");
        }

        DonationProjectContribution donationProjectContribution = postDonationProjectContributionRequestDto.toEntity(nickname);
        int affectedRows = donationProjectContributionMapper.insertContribution(donationProjectContribution);
        if(affectedRows != 1) {
            throw new DataSaveException("기부 모금 실패");
        }
    }

    public List<GetDonationProjectContributionsResponseDto> getContributionsByProjectId(int donationProjectId) {
        List<DonationProjectContribution> donationProjectContributions = donationProjectContributionMapper.selectContributionsByProjectId(donationProjectId);
        return donationProjectContributions.stream().map(DonationProjectContribution::toGetDonationProjectContributionsResponseDto).collect(Collectors.toList());
    }

    public void deleteContribution(int donationProjectContributionId) {
        donationProjectContributionMapper.deleteContribution(donationProjectContributionId);
    }
}
