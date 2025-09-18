package com.project.give.service;

import com.project.give.dto.donation.request.PostDonationProjectDetailRequestDto;
import com.project.give.dto.donation.response.GetDonationProjectDetailResponseDto;
import com.project.give.dto.donation.response.GetDonationProjectsResponseDto;
import com.project.give.entity.DonationProjectDetail;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.DonationProjectDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationProjectDetailService {

    @Autowired
    private DonationProjectDetailMapper donationProjectDetailMapper;

    public void createDonationProjectDetail(PostDonationProjectDetailRequestDto postDonationProjectDetailRequestDto) {
        DonationProjectDetail donationProjectDetail = postDonationProjectDetailRequestDto.toEntity();
        int affectedRows = donationProjectDetailMapper.insertDonationProjectDetail(donationProjectDetail);
        if(affectedRows != 1) {
            throw new DataSaveException("기부 상세페이지 저장 실패");
        }
    }

    public GetDonationProjectDetailResponseDto getDetailsByDonationProjectId (int donationProjectId) {
        return donationProjectDetailMapper.selectDonationProjectDetailById(donationProjectId).toGetDonationProjectDetailResponseDto();
    }

    public void deleteDonationProjectDetailById(int donationProjectId) {
        donationProjectDetailMapper.deleteDonationProjectDetailById(donationProjectId);
    }
}
