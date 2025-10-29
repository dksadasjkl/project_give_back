package com.project.give.controller.store;

import com.project.give.service.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/reviews")
public class StoreReviewController {

    @Autowired
    private StoreReviewService storeReviewService;

    // 💬 상품별 리뷰 + 평균 별점 조회
    @GetMapping("/{productId}")
    public ResponseEntity<?> getReviewsWithRatings(@PathVariable int productId) {
        return ResponseEntity.ok(storeReviewService.getReviewsWithRatings(productId));
    }
}
