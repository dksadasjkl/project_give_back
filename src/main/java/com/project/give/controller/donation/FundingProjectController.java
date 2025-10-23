package com.project.give.controller.donation;

import com.project.give.dto.donation.request.GetDonationProjectSearchRequestDto;
import com.project.give.dto.donation.request.PostDonationProjectRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectRequestDto;
import com.project.give.service.CategoryService;
import com.project.give.service.DonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fundings")
public class FundingProjectController {

    @Autowired
    private DonationProjectService donationProjectService;

    @Autowired
    private CategoryService donationCategoryService;

    @PostMapping
    public ResponseEntity<?> createFundingProject(@RequestBody PostDonationProjectRequestDto dto) {
        dto.setDonationProjectType("FUNDING"); // 타입 지정
        donationProjectService.createDonationProject(dto);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/{fundingProjectId}")
    public ResponseEntity<?> getFundingProject(@PathVariable int fundingProjectId) {
        return ResponseEntity.ok(donationProjectService.getDonationProject(fundingProjectId));
    }

    @GetMapping
    public ResponseEntity<?> getFundingProjects(GetDonationProjectSearchRequestDto dto) {
        return ResponseEntity.ok(donationProjectService.getDonationProjects("FUNDING"));
    }

    @DeleteMapping("/{fundingProjectId}")
    public ResponseEntity<?> deleteFundingProject(@PathVariable int fundingProjectId) {
        donationProjectService.deleteDonationProject(fundingProjectId);
        return ResponseEntity.ok("펀딩 프로젝트 삭제 완료");
    }

    @PutMapping("/{fundingProjectId}")
    public ResponseEntity<?> updateFundingProject(
            @PathVariable int fundingProjectId,
            @RequestBody PutDonationProjectRequestDto dto) {
        donationProjectService.updateDonationProject(fundingProjectId, dto);
        return ResponseEntity.ok("펀딩 프로젝트 수정 완료");
    }

    @GetMapping("/load-more")
    public ResponseEntity<?> loadMoreFundingProjects(GetDonationProjectSearchRequestDto dto) {
        dto.setDonationProjectType("FUNDING");
        return ResponseEntity.ok(donationProjectService.loadMoreDonationProjects(dto));
    }

    @GetMapping("/count")
    public ResponseEntity<?> getFundingProjectCount(GetDonationProjectSearchRequestDto dto) {
        dto.setDonationProjectType("FUNDING");
        return ResponseEntity.ok(donationProjectService.totalLoadDonationProjectCount(dto));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getFundingCategories() {
        return ResponseEntity.ok(donationCategoryService.getCategoriesByType("FUNDING"));
    }
}
