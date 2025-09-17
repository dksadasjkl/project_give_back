package com.project.give.controller.donation;

import com.project.give.service.DonationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donation-categories")
public class DonationCategoryController {

    @Autowired
    private DonationCategoryService donationCategoryService;

    @GetMapping
    public ResponseEntity<?> getAllDonationCategories() {
        return ResponseEntity.ok(donationCategoryService.getAllDonationCategories());
    }
}
