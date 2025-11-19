package com.project.give.service.admin.funding;

import com.project.give.dto.donation.request.AdminDonationCreateRequestDto;
import com.project.give.entity.*;

import java.util.List;
import java.util.Map;

public interface AdminFundingService {

    // 프로젝트 CRUD
    void insertProject(AdminDonationCreateRequestDto dto);
    Map<String, Object> getProjectList(int page, int size, Integer categoryId, Integer searchTypeId);
    DonationProject getProjectDetail(int projectId);
    void updateProject(int projectId, DonationProject dto);
    void deleteProject(int projectId);

    // 상세 내용
    List<DonationProjectDetail> getProjectDetails(int projectId);
    void insertProjectDetail(int projectId, DonationProjectDetail dto);
    void updateProjectDetail(int detailId, DonationProjectDetail dto);
    void deleteProjectDetail(int detailId);

    // 댓글
    Map<String, Object> getComments(int projectId, int page, int size);
    void deleteComment(int commentId);

    // 참여 내역
    Map<String, Object> getContributions(int projectId, int page, int size);
    void deleteContribution(int contributionId);

    // 리워드
    List<FundingProjectReward> getRewards(int projectId);
    void insertReward(int projectId, FundingProjectReward dto);
    void updateReward(int rewardId, FundingProjectReward dto);
    void deleteReward(int rewardId);
}
