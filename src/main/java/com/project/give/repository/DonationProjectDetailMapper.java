package com.project.give.repository;

import com.project.give.entity.DonationProjectDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DonationProjectDetailMapper {
    public int insertDonationProjectDetail(DonationProjectDetail donationProjectDetail);
    public DonationProjectDetail selectDonationProjectDetailById(@Param("donationProjectId") int donationProjectId);
    public int deleteDonationProjectDetailById(@Param("donationProjectDetailId") int donationProjectDetailId);
    public int updateDonationProjectDetail(DonationProjectDetail donationProjectDetail);
}
