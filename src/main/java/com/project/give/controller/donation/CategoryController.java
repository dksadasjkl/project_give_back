package com.project.give.controller.donation;

import com.project.give.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService donationCategoryService;

    @GetMapping
    public ResponseEntity<?> getAllDonationCategories() {
        return ResponseEntity.ok(donationCategoryService.getAllDonationCategories());
    }
}
