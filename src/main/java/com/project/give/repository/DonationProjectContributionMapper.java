package com.project.give.repository;

import com.project.give.entity.DonationProjectContribution;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonationProjectContributionMapper {
    public int insertContribution(DonationProjectContribution contribution);
}
