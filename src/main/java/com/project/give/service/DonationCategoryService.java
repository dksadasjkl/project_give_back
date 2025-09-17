package com.project.give.service;

import com.project.give.dto.donation.response.GetDonationCategoryResponseDto;
import com.project.give.entity.DonationCategory;
import com.project.give.repository.DonationCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationCategoryService {

    @Autowired
    private DonationCategoryMapper donationCategoryMapper;

    public List<GetDonationCategoryResponseDto> getAllDonationCategories() {
        List<DonationCategory> donationCategories = donationCategoryMapper.selectAllDonationCategories();
        return donationCategories.stream()
                .map(DonationCategory::toGetDonationCategorysResponseDto)
                .collect(Collectors.toList());
    }
}
