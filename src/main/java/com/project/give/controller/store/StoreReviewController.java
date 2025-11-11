package com.project.give.controller.store;

import com.project.give.service.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/reviews")
public class StoreReviewController {

    @Autowired
    private StoreReviewService storeReviewService;

    // 💬 리뷰 목록 + 평균 + 분포 + 페이지네이션
    @GetMapping("/{productId}")
    public ResponseEntity<?> getReviewsWithRatings(
            @PathVariable int productId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "latest") String sort
    ) {
        return ResponseEntity.ok(storeReviewService.getReviewsWithRatings(productId, page, size, sort));
    }
}
