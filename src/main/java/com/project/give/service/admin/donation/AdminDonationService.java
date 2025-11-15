package com.project.give.service.admin.donation;

import com.project.give.entity.*;

import java.util.Map;
import java.util.List;

public interface AdminDonationService {

    // 프로젝트 목록
    Map<String, Object> getProjectList(int page, int size, Integer categoryId, Integer searchTypeId, String projectType);

    // 프로젝트 상세
    DonationProject getProjectDetail(int projectId);

    // 수정 / 삭제
    void updateProject(int projectId, DonationProject dto);
    void deleteProject(int projectId);

    // 상세 정보
    List<DonationProjectDetail> getProjectDetails(int projectId);
    void insertProjectDetail(int projectId, DonationProjectDetail dto);
    void updateProjectDetail(int detailId, DonationProjectDetail dto);
    void deleteProjectDetail(int detailId);

    // 댓글
    Map<String, Object> getComments(int projectId, int page, int size);
    void deleteComment(int commentId);

    // 후원
    Map<String, Object> getContributions(int projectId, int page, int size);
    void deleteContribution(int contributionId);

    // 리워드
    void insertReward(int projectId, FundingProjectReward dto);
    void updateReward(int rewardId, FundingProjectReward dto);
    void deleteReward(int rewardId);
}
