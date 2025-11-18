package com.project.give.controller.admin.donation;

import com.project.give.dto.donation.request.AdminDonationCreateRequestDto;
import com.project.give.entity.DonationProject;
import com.project.give.entity.DonationProjectDetail;
import com.project.give.entity.FundingProjectReward;
import com.project.give.service.admin.donation.AdminDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/donation")
public class AdminDonationController {

    @Autowired
    private AdminDonationService adminDonationService;

    //  프로젝트 등록
    @PostMapping("/projects")
    public ResponseEntity<?> insertProject(@RequestBody AdminDonationCreateRequestDto dto) {
        adminDonationService.insertProject(dto);
        return ResponseEntity.ok("프로젝트가 등록되었습니다.");
    }

    // ✔ 전체 프로젝트 목록 조회 (기부 + 펀딩)
    @GetMapping
    public ResponseEntity<?> getProjects(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer searchTypeId,
            @RequestParam(required = false) String projectType // DONATION / FUNDING
    ) {
        return ResponseEntity.ok(adminDonationService.getProjectList(page, size, categoryId, searchTypeId, projectType));
    }

    // ✔ 프로젝트 상세 조회
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<?> getProjectDetail(@PathVariable int projectId) {
        return ResponseEntity.ok(adminDonationService.getProjectDetail(projectId));
    }

    // ✔ 프로젝트 수정
    @PutMapping("/projects/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable int projectId, @RequestBody DonationProject dto) {
        adminDonationService.updateProject(projectId, dto);
        return ResponseEntity.ok("프로젝트가 수정되었습니다.");
    }

    // ✔ 프로젝트 삭제
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable int projectId) {
        adminDonationService.deleteProject(projectId);
        return ResponseEntity.ok("프로젝트가 삭제되었습니다.");
    }

    // ✔ 상세 정보 조회
    @GetMapping("/projects/{projectId}/details")
    public ResponseEntity<?> getProjectDetails(@PathVariable int projectId) {
        return ResponseEntity.ok(adminDonationService.getProjectDetails(projectId));
    }

    // ✔ 상세 정보 수정/등록
    @PostMapping("/projects/{projectId}/details")
    public ResponseEntity<?> insertDetail(@PathVariable int projectId, @RequestBody DonationProjectDetail dto) {
        adminDonationService.insertProjectDetail(projectId, dto);
        return ResponseEntity.ok("상세 정보가 추가되었습니다.");
    }

    @PutMapping("/projects/details/{detailId}")
    public ResponseEntity<?> updateDetail(@PathVariable int detailId, @RequestBody DonationProjectDetail dto) {
        adminDonationService.updateProjectDetail(detailId, dto);
        return ResponseEntity.ok("상세 정보가 수정되었습니다.");
    }

    @DeleteMapping("/projects/details/{detailId}")
    public ResponseEntity<?> deleteDetail(@PathVariable int detailId) {
        adminDonationService.deleteProjectDetail(detailId);
        return ResponseEntity.ok("상세 정보가 삭제되었습니다.");
    }

    // ✔ 댓글 관리
    @GetMapping("/comments")
    public ResponseEntity<?> getComments(
            @RequestParam int projectId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(adminDonationService.getComments(projectId, page, size));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
        adminDonationService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }

    // ✔ 후원 내역 조회
    @GetMapping("/contributions")
    public ResponseEntity<?> getContributions(
            @RequestParam int projectId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(adminDonationService.getContributions(projectId, page, size));
    }

    @DeleteMapping("/contributions/{contributionId}")
    public ResponseEntity<?> deleteContribution(@PathVariable int contributionId) {
        adminDonationService.deleteContribution(contributionId);
        return ResponseEntity.ok("후원 내역이 삭제되었습니다.");
    }

    // ✔ 펀딩 리워드 관리
    @PostMapping("/projects/{projectId}/rewards")
    public ResponseEntity<?> insertReward(@PathVariable int projectId, @RequestBody FundingProjectReward dto) {
        adminDonationService.insertReward(projectId, dto);
        return ResponseEntity.ok("리워드가 등록되었습니다.");
    }

    @PutMapping("/rewards/{rewardId}")
    public ResponseEntity<?> updateReward(@PathVariable int rewardId, @RequestBody FundingProjectReward dto) {
        adminDonationService.updateReward(rewardId, dto);
        return ResponseEntity.ok("리워드가 수정되었습니다.");
    }

    @DeleteMapping("/rewards/{rewardId}")
    public ResponseEntity<?> deleteReward(@PathVariable int rewardId) {
        adminDonationService.deleteReward(rewardId);
        return ResponseEntity.ok("리워드가 삭제되었습니다.");
    }
}
