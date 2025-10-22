package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectRewardRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectRewardRequestDto;
import com.project.give.entity.DonationProjectReward;
import com.project.give.service.DonationProjectRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donations/rewards")
public class DonationProjectRewardController {

    @Autowired
    private DonationProjectRewardService donationProjectRewardService;

    @PostMapping
    public ResponseEntity<?> createReward(@RequestBody PostDonationProjectRewardRequestDto dto) {
        donationProjectRewardService.createDonationProjectReward(dto);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getRewards(@PathVariable int donationProjectId) {
        return ResponseEntity.ok(donationProjectRewardService.getDonationProjectRewards(donationProjectId));
    }

    @PutMapping
    public ResponseEntity<?> updateReward(@RequestBody PutDonationProjectRewardRequestDto dto) {
        donationProjectRewardService.updateDonationProjectReward(dto.toEntity());
        return ResponseEntity.ok("리워드 수정 완료");
    }

    @DeleteMapping("/{rewardId}")
    public ResponseEntity<?> deleteReward(@PathVariable int rewardId) {
        donationProjectRewardService.deleteDonationProjectReward(rewardId);
        return ResponseEntity.ok("리워드 삭제 완료");
    }
}
