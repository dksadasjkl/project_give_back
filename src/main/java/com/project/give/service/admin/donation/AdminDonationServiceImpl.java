package com.project.give.service.admin.donation;

import com.project.give.entity.*;
import com.project.give.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminDonationServiceImpl implements AdminDonationService {

    @Autowired
    private DonationProjectMapper projectMapper;
    @Autowired
    private DonationProjectDetailMapper detailMapper;
    @Autowired
    private DonationProjectCommentMapper commentMapper;
    @Autowired
    private DonationProjectContributionMapper contributionMapper;
    @Autowired
    private FundingProjectRewardMapper rewardMapper;

    // ✔ 프로젝트 목록
    @Override
    public Map<String, Object> getProjectList(int page, int size, Integer categoryId, Integer searchTypeId, String type) {
        int startIndex = (page - 1) * size;

        List<DonationProject> list = projectMapper.selectDonationProjectsWithPaging(
                startIndex,
                size,
                categoryId != null ? categoryId : 0,
                searchTypeId != null ? searchTypeId : 1,
                type
        );

        int total = projectMapper.selectDonationProjectCount(
                categoryId != null ? categoryId : 0,
                type
        );

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    // ✔ 프로젝트 상세
    @Override
    public DonationProject getProjectDetail(int projectId) {
        return projectMapper.selectDonationProjectById(projectId);
    }

    @Override
    public void updateProject(int projectId, DonationProject dto) {
        dto.setDonationProjectId(projectId);
        projectMapper.updateDonationProject(dto);
    }

    @Override
    public void deleteProject(int projectId) {
        projectMapper.deleteDonationProjectById(projectId);
    }

    // ✔ 상세 정보 조회
    @Override
    public List<DonationProjectDetail> getProjectDetails(int projectId) {
        return detailMapper.selectDonationProjectDetailById(projectId);
    }

    @Override
    public void insertProjectDetail(int projectId, DonationProjectDetail dto) {
        dto.setDonationProjectId(projectId);
        detailMapper.insertDonationProjectDetail(dto);
    }

    @Override
    public void updateProjectDetail(int detailId, DonationProjectDetail dto) {
        dto.setDonationProjectDetailId(detailId);
        detailMapper.updateDonationProjectDetail(dto);
    }

    @Override
    public void deleteProjectDetail(int detailId) {
        detailMapper.deleteDonationProjectDetailById(detailId);
    }

    // ✔ 댓글 관리
    @Override
    public Map<String, Object> getComments(int projectId, int page, int size) {
        int startIndex = (page - 1) * size;

        List<DonationProjectComment> list = commentMapper.selectCommentsWithPaging(
                startIndex,
                size,
                projectId
        );

        int total = commentMapper.selectCommentCount(projectId);

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    @Override
    public void deleteComment(int commentId) {
        commentMapper.deleteDonationProjectComment(commentId);
    }

    // ✔ 후원 관리
    @Override
    public Map<String, Object> getContributions(int projectId, int page, int size) {
        int startIndex = (page - 1) * size;

        List<DonationProjectContribution> list = contributionMapper.selectContributionsWithPaging(
                startIndex,
                size,
                projectId
        );

        int total = contributionMapper.selectContributionCount(projectId);

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    @Override
    public void deleteContribution(int contributionId) {
        contributionMapper.deleteContribution(contributionId);
    }

    // ✔ 리워드
    @Override
    public void insertReward(int projectId, FundingProjectReward dto) {
        dto.setDonationProjectId(projectId);
        rewardMapper.insertFundingProjectReward(dto);
    }

    @Override
    public void updateReward(int rewardId, FundingProjectReward dto) {
        dto.setFundingProjectRewardId(rewardId);
        rewardMapper.updateFundingProjectReward(dto);
    }

    @Override
    public void deleteReward(int rewardId) {
        rewardMapper.deleteFundingProjectReward(rewardId);
    }
}
