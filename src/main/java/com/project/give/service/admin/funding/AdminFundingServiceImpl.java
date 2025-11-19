package com.project.give.service.admin.funding;

import com.project.give.dto.donation.request.AdminDonationCreateRequestDto;
import com.project.give.entity.*;
import com.project.give.repository.*;
import com.project.give.service.admin.donation.AdminDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminFundingServiceImpl implements AdminFundingService {

    private static final String FUNDING_TYPE = "FUNDING";

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

    @Override
    public void insertProject(AdminDonationCreateRequestDto dto) {

        DonationProject project = dto.toProjectEntity();

        project.setDonationProjectType(FUNDING_TYPE);
        project.setDonationProjectCurrentAmount(0);

        projectMapper.insertDonationProject(project);

        // 상세 콘텐츠
        if (dto.getDetails() != null) {
            for (AdminDonationCreateRequestDto.Detail d : dto.getDetails()) {
                DonationProjectDetail detail = d.toEntity(project.getDonationProjectId());
                detailMapper.insertDonationProjectDetail(detail);
            }
        }

        // 리워드
        if (dto.getRewards() != null) {
            for (AdminDonationCreateRequestDto.Reward r : dto.getRewards()) {
                FundingProjectReward reward = r.toEntity(project.getDonationProjectId());
                rewardMapper.insertFundingProjectReward(reward);
            }
        }
    }

    @Override
    public Map<String, Object> getProjectList(int page, int size, Integer categoryId, Integer searchTypeId) {
        int startIndex = (page - 1) * size;

        List<DonationProject> list = projectMapper.selectDonationProjectsWithPaging(
                startIndex, size,
                categoryId != null ? categoryId : 0,
                searchTypeId != null ? searchTypeId : 1,
                FUNDING_TYPE
        );

        int total = projectMapper.selectDonationProjectCount(
                categoryId != null ? categoryId : 0,
                FUNDING_TYPE
        );

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    @Override
    public DonationProject getProjectDetail(int projectId) {
        return projectMapper.selectDonationProjectById(projectId);
    }

    @Override
    public void updateProject(int projectId, DonationProject dto) {
        dto.setDonationProjectId(projectId);
        dto.setDonationProjectType(FUNDING_TYPE); // 안전하게 고정
        projectMapper.updateDonationProject(dto);
    }

    @Override
    public void deleteProject(int projectId) {
        projectMapper.deleteDonationProjectById(projectId);
    }

    // 상세 콘텐츠
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

    // 댓글
    @Override
    public Map<String, Object> getComments(int projectId, int page, int size) {
        int startIndex = (page - 1) * size;

        List<DonationProjectComment> list = commentMapper.selectCommentsWithPaging(
                startIndex, size, projectId
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

    // 후원
    @Override
    public Map<String, Object> getContributions(int projectId, int page, int size) {
        int startIndex = (page - 1) * size;

        List<DonationProjectContribution> list = contributionMapper.selectContributionsWithPaging(
                startIndex, size, projectId
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

    // 리워드

    @Override
    public List<FundingProjectReward> getRewards(int projectId) {
        return rewardMapper.selectFundingProjectRewards(projectId);
    }

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
