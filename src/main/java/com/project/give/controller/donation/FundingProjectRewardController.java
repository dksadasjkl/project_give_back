package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostFundingProjectRewardRequestDto;
import com.project.give.dto.donation.request.PutFundingProjectRewardRequestDto;
import com.project.give.service.FundingProjectRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donations/rewards")
public class FundingProjectRewardController {

    @Autowired
    private FundingProjectRewardService donationProjectRewardService;

    @PostMapping
    public ResponseEntity<?> createReward(@RequestBody PostFundingProjectRewardRequestDto dto) {
        System.out.println(dto);
        donationProjectRewardService.createDonationProjectReward(dto);

        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getRewards(@PathVariable int donationProjectId) {
        return ResponseEntity.ok(donationProjectRewardService.getDonationProjectRewards(donationProjectId));
    }

    @PutMapping
    public ResponseEntity<?> updateReward(@RequestBody PutFundingProjectRewardRequestDto dto) {
        donationProjectRewardService.updateDonationProjectReward(dto.toEntity());
        return ResponseEntity.ok("리워드 수정 완료");
    }

    @DeleteMapping("/{fundingProjectRewardId}")
    public ResponseEntity<?> deleteReward(@PathVariable int fundingProjectRewardId) {
        donationProjectRewardService.deleteDonationProjectReward(fundingProjectRewardId);
        return ResponseEntity.ok("리워드 삭제 완료");
    }
}
