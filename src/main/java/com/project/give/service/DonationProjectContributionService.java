package com.project.give.service;

import com.project.give.dto.donation.request.GetDonationContributionSearchRequestDto;
import com.project.give.dto.donation.request.GetDonationProjectSearchRequestDto;
import com.project.give.dto.donation.request.PostDonationProjectContributionRequestDto;
import com.project.give.dto.donation.response.GetDonationProjectContributionsResponseDto;
import com.project.give.dto.donation.response.GetDonationProjectCountResponseDto;
import com.project.give.dto.donation.response.GetDonationProjectsResponseDto;
import com.project.give.entity.DonationProject;
import com.project.give.entity.DonationProjectContribution;
import com.project.give.entity.User;
import com.project.give.exception.DataNotFoundException;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.DonationProjectContributionMapper;
import com.project.give.repository.DonationProjectMapper;
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
    @Autowired
    private DonationProjectMapper donationProjectMapper;

    public void createDonationProjectContribution(PostDonationProjectContributionRequestDto postDonationProjectContributionRequestDto) {
        User user = userMapper.findNicknameByUserId(postDonationProjectContributionRequestDto.getUserId());
        String nickname = user.getNickname();
        if (nickname == null) {
            throw new DataNotFoundException("사용자 정보가 없습니다.");
        }

        DonationProjectContribution donationProjectContribution = postDonationProjectContributionRequestDto.toEntity(nickname);
        int affectedRows = donationProjectContributionMapper.insertContribution(donationProjectContribution);
        if(affectedRows != 1) {
            throw new DataSaveException("기부 모금 실패");
        }

        // 2. 모금액 업데이트
        donationProjectMapper.updateDonationCurrentAmount(
                postDonationProjectContributionRequestDto.getDonationProjectId(),
                postDonationProjectContributionRequestDto.getDonationProjectContributionAmount()
        );
    }

    public List<GetDonationProjectContributionsResponseDto> getContributionsByProjectId(int donationProjectId) {
        List<DonationProjectContribution> donationProjectContributions = donationProjectContributionMapper.selectContributionsByProjectId(donationProjectId);
        return donationProjectContributions.stream().map(DonationProjectContribution::toGetDonationProjectContributionsResponseDto).collect(Collectors.toList());
    }

    public void deleteContribution(int donationProjectContributionId) {
        donationProjectContributionMapper.deleteContribution(donationProjectContributionId);
    }

    public List<GetDonationProjectContributionsResponseDto> loadMoreContributions(GetDonationContributionSearchRequestDto getDonationContributionSearchRequestDto) {
        List<DonationProjectContribution> donationProjectContributions = donationProjectContributionMapper.selectContributionsWithPaging(
                getDonationContributionSearchRequestDto.getStartIndex(),
                getDonationContributionSearchRequestDto.getCount(),
                getDonationContributionSearchRequestDto.getDonationProjectId()
        );
        return donationProjectContributions.stream().map(DonationProjectContribution::toGetDonationProjectContributionsResponseDto).collect(Collectors.toList());
    }

    public GetDonationProjectCountResponseDto totalLoadContributionCountCount(GetDonationContributionSearchRequestDto getDonationContributionSearchRequestDto) {
        int totalCount = donationProjectContributionMapper.selectContributionCount(getDonationContributionSearchRequestDto.getDonationProjectId());
        int totalLoadCount = (int) Math.ceil(((double) totalCount) / getDonationContributionSearchRequestDto.getCount());

        return GetDonationProjectCountResponseDto.builder()
                .totalCount(totalCount)
                .totalLoadCount(totalLoadCount)
                .build();
    }
}
