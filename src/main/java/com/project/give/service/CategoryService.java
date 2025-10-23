package com.project.give.service;

import com.project.give.dto.donation.response.GetDonationCategoryResponseDto;
import com.project.give.entity.DonationCategory;
import com.project.give.repository.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper donationCategoryMapper;

    public List<GetDonationCategoryResponseDto> getCategoriesByType(String categoryType) {
        List<DonationCategory> donationCategories = donationCategoryMapper.getCategoriesByType(categoryType);
        return donationCategories.stream()
                .map(DonationCategory::toGetDonationCategorysResponseDto)
                .collect(Collectors.toList());
    }

    public List<GetDonationCategoryResponseDto> getAllDonationCategories() {
        List<DonationCategory> donationCategories = donationCategoryMapper.selectAllDonationCategories();
        return donationCategories.stream()
                .map(DonationCategory::toGetDonationCategorysResponseDto)
                .collect(Collectors.toList());
    }
}
