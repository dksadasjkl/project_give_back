package com.project.give.repository;

import com.project.give.entity.DonationProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationProjectMapper {
    public int insertDonationProject(DonationProject donationProject);
    public DonationProject selectDonationProjectById(@Param("donationProjectId") int donationProjectId);
    public List<DonationProject> selectAllDonationProjects(@Param("donationProjectType") String donationProjectType);
    public int deleteDonationProjectById(@Param("donationProjectId") int donationProjectId);
    public int updateDonationProject(DonationProject donationProject);

    List<DonationProject> selectDonationProjectsWithPaging(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("donationCategoryId") int donationCategoryId,
            @Param("searchTypeId") int searchTypeId,
            @Param("donationProjectType") String donationProjectType
    );

    int selectDonationProjectCount(
            @Param("donationCategoryId") int donationCategoryId,
            @Param("donationProjectType") String donationProjectType
    );

    public int updateDonationCurrentAmount(@Param("donationProjectId") int donationProjectId,
                                           @Param("donationProjectContributionAmount") int donationProjectContributionAmount);

    public List<DonationProject> findMyDonationsByUserId(@Param("userId") int userId);



    public List<DonationProject> findMyFundingsByUserId(@Param("userId") int userId);


    // 내가 참여한 기부 (페이징)
    List<DonationProject> findMyDonationsPagedByUserId(@Param("userId") int userId,
                                                       @Param("offset") int offset,
                                                       @Param("size") int size);

    int countMyDonations(@Param("userId") int userId);

    // 내가 참여한 펀딩 (페이징)
    List<DonationProject> findMyFundingsPagedByUserId(@Param("userId") int userId,
                                                      @Param("offset") int offset,
                                                      @Param("size") int size);

    int countMyFundings(@Param("userId") int userId);

    DonationProject selectTopDonationProject();
    List<DonationProject> selectTopDonationProjects(@Param("limit") int limit);

    DonationProject selectTopFundingProject();
    List<DonationProject> selectTopFundingProjects(@Param("limit") int limit);
}