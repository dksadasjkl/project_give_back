package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectContributionRequestDto;
import com.project.give.service.DonationProjectContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
