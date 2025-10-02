package com.project.give.service;

import com.project.give.dto.donation.request.PostDonationProjectDetailRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectDetailRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectRequestDto;
import com.project.give.dto.donation.response.GetDonationProjectDetailResponseDto;
import com.project.give.dto.donation.response.GetDonationProjectsResponseDto;
import com.project.give.entity.DonationProjectDetail;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.DonationProjectDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationProjectDetailService {

    @Autowired
    private DonationProjectDetailMapper donationProjectDetailMapper;

    @Transactional(rollbackFor = Exception.class)
    public void createDonationProjectDetail(PostDonationProjectDetailRequestDto postDonationProjectDetailRequestDto) {
        DonationProjectDetail donationProjectDetail = postDonationProjectDetailRequestDto.toEntity();
        int affectedRows = donationProjectDetailMapper.insertDonationProjectDetail(donationProjectDetail);
        if(affectedRows != 1) {
            throw new DataSaveException("기부 상세페이지 저장 실패");
        }
    }

    // 배치 저장
    @Transactional(rollbackFor = Exception.class)
    public void createDonationProjectDetailBatch(List<PostDonationProjectDetailRequestDto> dtoList) {
        // DTO -> Entity 변환
        List<DonationProjectDetail> entities = dtoList.stream()
                .map(PostDonationProjectDetailRequestDto::toEntity)
                .collect(Collectors.toList());

        // MyBatis <foreach>로 반복 insert
        int affectedRows = donationProjectDetailMapper.insertBatch(entities);

        if (affectedRows != entities.size()) {
            throw new DataSaveException("기부 상세페이지 배치 저장 실패");
        }
    }





    public GetDonationProjectDetailResponseDto getDetailsByDonationProjectId (int donationProjectId) {
        return donationProjectDetailMapper.selectDonationProjectDetailById(donationProjectId).toGetDonationProjectDetailResponseDto();
    }

    public void deleteDonationProjectDetailById(int donationProjectId) {
        donationProjectDetailMapper.deleteDonationProjectDetailById(donationProjectId);
    }

    public void updateDonationProjectDetail (int donationProjectDetailId, PutDonationProjectDetailRequestDto putDonationProjectDetailRequestDto) {
        donationProjectDetailMapper.updateDonationProjectDetail(putDonationProjectDetailRequestDto.toEntity(donationProjectDetailId));
    }
}
