package com.project.give.repository;

import com.project.give.entity.DonationCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DonationCategoryMapper {
    List<DonationCategory> selectAllDonationCategories();
}
