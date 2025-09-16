package com.project.give.service;

import com.project.give.dto.donation.request.PostDonationProjectRequestDto;
import com.project.give.entity.DonationProject;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.DonationProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationProjectService {

    @Autowired
    private DonationProjectMapper donationProjectMapper;

    public void createDonationProject(PostDonationProjectRequestDto postDonationProjectRequestDto) {
        DonationProject donationProject = postDonationProjectRequestDto.toEntity();
        if(postDonationProjectRequestDto.getDonationCategoryId() == 0) {
            throw new IllegalArgumentException("카테고리가 선택되지 않았습니다.");
        }

        int affectedRows = donationProjectMapper.insertDonationProject(donationProject);
        if(affectedRows != 1) {
            throw new DataSaveException("DonationProject 저장 실패");
        }
    }

    public DonationProject getDonationProject (int donationProjectId) {
        return donationProjectMapper.selectDonationProjectById(donationProjectId);
    }
}
