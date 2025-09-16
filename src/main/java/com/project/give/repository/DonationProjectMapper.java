package com.project.give.repository;

import com.project.give.entity.DonationProject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonationProjectMapper {
    public int insertDonationProject(DonationProject project);
    public DonationProject selectDonationProjectById(int donationProjectId);
}
