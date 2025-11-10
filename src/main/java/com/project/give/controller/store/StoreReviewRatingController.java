package com.project.give.controller.store;

import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/review-rating")
public class StoreReviewRatingController {

    @Autowired
    private StoreReviewRatingService ratingService;

    // ⭐ 별점 등록 및 수정
    @PostMapping("/{commentId}")
    public ResponseEntity<?> createOrUpdateRating(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable int commentId,
            @RequestParam int rating
    ) {
        ratingService.createRating(principalUser.getUserId(), commentId, rating);
        return ResponseEntity.ok("별점이 등록 또는 수정되었습니다.");
    }

    // 댓글별 평균 별점 조회
    @GetMapping("/{commentId}/average")
    public ResponseEntity<?> getAverageRating(@PathVariable int commentId) {
        return ResponseEntity.ok(ratingService.getAverageRating(commentId));
    }
}
