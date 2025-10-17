package com.project.give.repository;

import com.project.give.entity.DonationProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationProjectMapper {
    public int insertDonationProject(DonationProject donationProject);
    public DonationProject selectDonationProjectById(@Param("donationProjectId") int donationProjectId);
    public List<DonationProject> selectAllDonationProjects();
    public int deleteDonationProjectById(@Param("donationProjectId") int donationProjectId);
    public int updateDonationProject(DonationProject donationProject);
    public List<DonationProject> selectDonationProjectsWithPaging (
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("donationCategoryId") int donationCategoryId,
            @Param("searchTypeId") int searchTypeId
            );
    public int selectDonationProjectCount(@Param("donationCategoryId") int donationCategoryId);
    public int updateDonationCurrentAmount(@Param("donationProjectId") int donationProjectId,
                                           @Param("donationProjectContributionAmount") int donationProjectContributionAmount);

    public List<DonationProject> findMyDonationsByUserId(@Param("userId") int userId);
}