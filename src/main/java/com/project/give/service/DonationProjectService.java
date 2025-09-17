package com.project.give.service;

import com.project.give.dto.donation.request.GetDonationProjectSearchRequestDto;
import com.project.give.dto.donation.request.PostDonationProjectRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectRequestDto;
import com.project.give.dto.donation.response.GetDonationProjectsResponseDto;
import com.project.give.entity.DonationProject;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.DonationProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public GetDonationProjectsResponseDto getDonationProject (int donationProjectId) {
        return donationProjectMapper.selectDonationProjectById(donationProjectId).toGetDonationProjectsResponseDto();
    }

    public List<GetDonationProjectsResponseDto> getDonationProjects () {
        List<DonationProject> donationProjects = donationProjectMapper.selectAllDonationProjects();
        return donationProjects.stream().map(DonationProject::toGetDonationProjectsResponseDto).collect(Collectors.toList());
    }

    public List<GetDonationProjectsResponseDto> loadMoreDonationProjects(GetDonationProjectSearchRequestDto getDonationProjectSearchRequestDto) {
        System.out.println(getDonationProjectSearchRequestDto);
        List<DonationProject> donationProjects = donationProjectMapper.selectDonationProjectsWithPaging(
                getDonationProjectSearchRequestDto.getStartIndex(),
                getDonationProjectSearchRequestDto.getCount(),
                getDonationProjectSearchRequestDto.getDonationCategoryId(),
                getDonationProjectSearchRequestDto.getSearchTypeId()
        );
        return donationProjects.stream().map(DonationProject::toGetDonationProjectsResponseDto).collect(Collectors.toList());
    }

    public void deleteDonationProject(int donationProjectId) {
        donationProjectMapper.deleteDonationProjectById(donationProjectId);
    }

    public void updateDonationProject (int donationProjectId, PutDonationProjectRequestDto putDonationProjectRequestDto) {
        donationProjectMapper.updateDonationProject(putDonationProjectRequestDto.toEntity(donationProjectId));
    }
}
