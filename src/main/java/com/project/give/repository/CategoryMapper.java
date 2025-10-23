package com.project.give.repository;

import com.project.give.entity.DonationCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<DonationCategory> getCategoriesByType(@Param("categoryType") String categoryType);
    List<DonationCategory> selectAllDonationCategories();
}
