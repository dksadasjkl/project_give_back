package com.project.give.controller.admin.funding;

import com.project.give.dto.donation.request.AdminDonationCreateRequestDto;
import com.project.give.entity.DonationProject;
import com.project.give.entity.DonationProjectDetail;
import com.project.give.entity.FundingProjectReward;
import com.project.give.service.CategoryService;
import com.project.give.service.admin.funding.AdminFundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/funding")
public class AdminFundingController {

    @Autowired
    private AdminFundingService adminFundingService;

    @Autowired
    private CategoryService donationCategoryService;

    /** 생성 */
    @PostMapping("/projects")
    public ResponseEntity<?> insertProject(@RequestBody AdminDonationCreateRequestDto dto) {
        dto.setDonationProjectType("FUNDING");
        adminFundingService.insertProject(dto);
        return ResponseEntity.ok("펀딩 프로젝트가 등록되었습니다.");
    }

    /** 목록 */
    @GetMapping
    public ResponseEntity<?> getProjects(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer searchTypeId
    ) {
        return ResponseEntity.ok(
                adminFundingService.getProjectList(page, size, categoryId, searchTypeId)
        );
    }

    /** 상세 */
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<?> getProjectDetail(@PathVariable int projectId) {
        return ResponseEntity.ok(adminFundingService.getProjectDetail(projectId));
    }

    /** 수정 */
    @PutMapping("/projects/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable int projectId, @RequestBody DonationProject dto) {
        adminFundingService.updateProject(projectId, dto);
        return ResponseEntity.ok("펀딩이 수정되었습니다.");
    }

    /** 삭제 */
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable int projectId) {
        adminFundingService.deleteProject(projectId);
        return ResponseEntity.ok("펀딩이 삭제되었습니다.");
    }

    /** 상세 콘텐츠 */
    @GetMapping("/projects/{projectId}/details")
    public ResponseEntity<?> getDetails(@PathVariable int projectId) {
        return ResponseEntity.ok(adminFundingService.getProjectDetails(projectId));
    }

    @PostMapping("/projects/{projectId}/details")
    public ResponseEntity<?> insertDetail(@PathVariable int projectId, @RequestBody DonationProjectDetail dto) {
        adminFundingService.insertProjectDetail(projectId, dto);
        return ResponseEntity.ok("상세 추가 완료");
    }

    @PutMapping("/projects/details/{detailId}")
    public ResponseEntity<?> updateDetail(@PathVariable int detailId, @RequestBody DonationProjectDetail dto) {
        adminFundingService.updateProjectDetail(detailId, dto);
        return ResponseEntity.ok("상세 수정 완료");
    }

    @DeleteMapping("/projects/details/{detailId}")
    public ResponseEntity<?> deleteDetail(@PathVariable int detailId) {
        adminFundingService.deleteProjectDetail(detailId);
        return ResponseEntity.ok("상세 삭제 완료");
    }

    /** 댓글 */
    @GetMapping("/comments")
    public ResponseEntity<?> getComments(
            @RequestParam int projectId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(adminFundingService.getComments(projectId, page, size));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
        adminFundingService.deleteComment(commentId);
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    /** 참여내역 */
    @GetMapping("/contributions")
    public ResponseEntity<?> getContributions(
            @RequestParam int projectId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(adminFundingService.getContributions(projectId, page, size));
    }

    @DeleteMapping("/contributions/{id}")
    public ResponseEntity<?> deleteContribution(@PathVariable int id) {
        adminFundingService.deleteContribution(id);
        return ResponseEntity.ok("참여내역 삭제 완료");
    }

    /** 리워드 */

    @GetMapping("/projects/{projectId}/rewards")
    public ResponseEntity<?> getRewards(@PathVariable int projectId) {
        return ResponseEntity.ok(adminFundingService.getRewards(projectId));
    }

    @PostMapping("/projects/{projectId}/rewards")
    public ResponseEntity<?> insertReward(@PathVariable int projectId, @RequestBody FundingProjectReward dto) {
        adminFundingService.insertReward(projectId, dto);
        return ResponseEntity.ok("리워드 등록 완료");
    }


    @PutMapping("/rewards/{rewardId}")
    public ResponseEntity<?> updateReward(@PathVariable int rewardId, @RequestBody FundingProjectReward dto) {
        adminFundingService.updateReward(rewardId, dto);
        return ResponseEntity.ok("리워드 수정 완료");
    }

    @DeleteMapping("/rewards/{rewardId}")
    public ResponseEntity<?> deleteReward(@PathVariable int rewardId) {
        adminFundingService.deleteReward(rewardId);
        return ResponseEntity.ok("리워드 삭제 완료");
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAdminDonationCategories() {
        return ResponseEntity.ok(donationCategoryService.getCategoriesByType("FUNDING"));
    }
}

