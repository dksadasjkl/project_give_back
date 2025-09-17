package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectRequestDto;
import com.project.give.entity.DonationProject;
import com.project.give.service.DonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> getDonationProjects() {
        return ResponseEntity.ok(donationProjectService.getDonationProjects());
    }

    @DeleteMapping("/{donationProjectId}")
    public ResponseEntity<?> deleteDonationProject(@PathVariable int donationProjectId) {
        donationProjectService.deleteDonationProject(donationProjectId);
        return ResponseEntity.ok("Donation project deleted successfully");
    }

    @PutMapping("/{donationProjectId}")
    public ResponseEntity<?> updateDonationProject (
            @PathVariable int donationProjectId,
            @RequestBody PutDonationProjectRequestDto putDonationProjectRequestDto) {
        donationProjectService.updateDonationProject(donationProjectId, putDonationProjectRequestDto);
        return ResponseEntity.ok("Donation project updated successfully");
    }
}
