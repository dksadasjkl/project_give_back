package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectRequestDto;
import com.project.give.service.DonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donations")
public class DonationProjectController {

    @Autowired
    private DonationProjectService donationProjectService;

    @PostMapping
    public ResponseEntity<?> createDonationProject(@RequestBody PostDonationProjectRequestDto postDonationProjectRequestDto) {
        donationProjectService.createDonationProject(postDonationProjectRequestDto);
        return ResponseEntity.created(null).body(true);
    };
}
