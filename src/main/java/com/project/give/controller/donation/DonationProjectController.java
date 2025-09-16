package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectRequestDto;
import com.project.give.service.DonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getDonationProject(@PathVariable int donationProjectId) {
       return ResponseEntity.ok(donationProjectService.getDonationProject(donationProjectId));
    };
}
