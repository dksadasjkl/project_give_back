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
@RequestMapping("/donations")
public class DonationProjectController {

    @Autowired
    private DonationProjectService donationProjectService;

    @Autowired
    private CategoryService donationCategoryService;

    @PostMapping
    public ResponseEntity<?> createDonationProject(@RequestBody PostDonationProjectRequestDto postDonationProjectRequestDto) {
        donationProjectService.createDonationProject(postDonationProjectRequestDto);
        return ResponseEntity.created(null).body(true);
    };

    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getDonationProject(@PathVariable int donationProjectId) {
       return ResponseEntity.ok(donationProjectService.getDonationProject(donationProjectId));
    };

    @GetMapping
    public ResponseEntity<?> getDonationProjects() {
        return ResponseEntity.ok(donationProjectService.getDonationProjects("DONATION"));
    }

    @DeleteMapping("/{donationProjectId}")
    public ResponseEntity<?> deleteDonationProject(@PathVariable int donationProjectId) {
        donationProjectService.deleteDonationProject(donationProjectId);
        return ResponseEntity.ok("기부 프로젝트 삭제 완료");
    }

    @PutMapping("/{donationProjectId}")
    public ResponseEntity<?> updateDonationProject (
            @PathVariable int donationProjectId,
            @RequestBody PutDonationProjectRequestDto putDonationProjectRequestDto) {
        donationProjectService.updateDonationProject(donationProjectId, putDonationProjectRequestDto);
        return ResponseEntity.ok("기부 프로젝트 수정 완료");
    }

    @GetMapping("/load-more")
    public ResponseEntity<?> loadMoreDonationProjects(GetDonationProjectSearchRequestDto getDonationProjectSearchRequestDto) {
        getDonationProjectSearchRequestDto.setDonationProjectType("DONATION");
        return ResponseEntity.ok(donationProjectService.loadMoreDonationProjects(getDonationProjectSearchRequestDto));
    }

    @GetMapping("/count")
    public ResponseEntity<?> getProductCount(GetDonationProjectSearchRequestDto getDonationProjectSearchRequestDto) {
        getDonationProjectSearchRequestDto.setDonationProjectType("DONATION");
        return ResponseEntity.ok(donationProjectService.totalLoadDonationProjectCount(getDonationProjectSearchRequestDto));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getDonationCategories() {
        return ResponseEntity.ok(donationCategoryService.getCategoriesByType("DONATION"));
    }

}
