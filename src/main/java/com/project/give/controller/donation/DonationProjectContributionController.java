package com.project.give.controller.donation;

import com.project.give.dto.donation.request.GetDonationContributionSearchRequestDto;
import com.project.give.dto.donation.request.GetDonationProjectSearchRequestDto;
import com.project.give.dto.donation.request.PostDonationProjectContributionRequestDto;
import com.project.give.service.DonationProjectContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donation-project-contributions")
public class DonationProjectContributionController {

    @Autowired
    private DonationProjectContributionService donationProjectContributionService;

    @PostMapping
    public ResponseEntity<?> createDonationProjectContribution(@RequestBody PostDonationProjectContributionRequestDto postDonationProjectContributionRequestDto) {
        donationProjectContributionService.createDonationProjectContribution(postDonationProjectContributionRequestDto);
        return ResponseEntity.created(null).body("기부 등록 완료");
    }

    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getContributions(@PathVariable int donationProjectId) {
        return ResponseEntity.ok(donationProjectContributionService.getContributionsByProjectId(donationProjectId));
    }

    @DeleteMapping("/{donationProjectContributionId}")
    public ResponseEntity<?> deleteContribution(@PathVariable int donationProjectContributionId) {
        donationProjectContributionService.deleteContribution(donationProjectContributionId);
        return ResponseEntity.ok("참여 내역 삭제 완료");
    }

    // 수정(update) 기능은 기부 플랫폼 특성상 필요하지 않음
    // => 기부 금액을 변경하는 것은 일반적으로 허용되지 않으므로, update 메서드는 구현하지 않음

    @GetMapping("/load-more")
    public ResponseEntity<?> loadMoreContributions(GetDonationContributionSearchRequestDto getDonationContributionSearchRequestDto) {
        return ResponseEntity.ok(donationProjectContributionService.loadMoreContributions(getDonationContributionSearchRequestDto));
    }

    @GetMapping("/count")
    public ResponseEntity<?> getContributionCount(GetDonationContributionSearchRequestDto getDonationContributionSearchRequestDto) {
        return ResponseEntity.ok(donationProjectContributionService.totalLoadContributionCountCount(getDonationContributionSearchRequestDto));
    }
}
