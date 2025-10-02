package com.project.give.repository;

import com.project.give.entity.DonationProjectDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationProjectDetailMapper {
    public int insertDonationProjectDetail(DonationProjectDetail donationProjectDetail);
    public int insertBatch(List<DonationProjectDetail> entities);

    public List<DonationProjectDetail> selectDonationProjectDetailById(@Param("donationProjectId") int donationProjectId);
    public int deleteDonationProjectDetailById(@Param("donationProjectDetailId") int donationProjectDetailId);
    public int updateDonationProjectDetail(DonationProjectDetail donationProjectDetail);
}
