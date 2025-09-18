package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectDetailRequestDto;
import com.project.give.entity.DonationProjectDetail;
import com.project.give.service.DonationProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donation-project-details")
public class DonationProjectDetailController {

    @Autowired
    private DonationProjectDetailService donationProjectDetailService;

    @PostMapping
    public ResponseEntity<?> createDetail(@RequestBody PostDonationProjectDetailRequestDto postDonationProjectDetailRequestDto) {
        donationProjectDetailService.createDonationProjectDetail(postDonationProjectDetailRequestDto);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getDetailsByDonationProjectId (@PathVariable int donationProjectId) {
        return ResponseEntity.ok(donationProjectDetailService.getDetailsByDonationProjectId(donationProjectId));
    }

    @DeleteMapping("/{donationProjectDetailId}")
    public ResponseEntity<?> deleteDonationProjectDetailById(@PathVariable int donationProjectDetailId) {
        donationProjectDetailService.deleteDonationProjectDetailById(donationProjectDetailId);
        return ResponseEntity.ok("기부 상세페이지 삭제 완료");
    }

}
