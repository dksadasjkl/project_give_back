package com.project.give.controller.store;

import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    // 🛒 리뷰 작성 자격 확인 (상품 구매자만 true)
    @GetMapping("/{productId}/eligibility")
    public ResponseEntity<?> checkReviewEligibility(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable int productId
    ) {
        boolean eligible = storeReviewService.checkReviewEligibility(principalUser.getUserId(), productId);
        Map<String, Boolean> result = new HashMap<>();
        result.put("eligible", eligible);
        return ResponseEntity.ok(result);
    }
}
