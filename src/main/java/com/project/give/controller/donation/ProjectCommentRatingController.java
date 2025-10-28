package com.project.give.controller.donation;

import com.project.give.entity.PrincipalUser;
import com.project.give.service.ProjectCommentRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/review-rating")
public class ProjectCommentRatingController {

    @Autowired
    private ProjectCommentRatingService ratingService;

    // 별점 등록
    @PostMapping("/{commentId}")
    public ResponseEntity<?> createRating(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable int commentId,
            @RequestParam int rating) {
        ratingService.createRating(principalUser.getUserId(), commentId, rating);
        return ResponseEntity.ok("별점이 등록되었습니다.");
    }

    // 댓글별 평균 별점 조회
    @GetMapping("/{commentId}/average")
    public ResponseEntity<?> getAverageRating(@PathVariable int commentId) {
        return ResponseEntity.ok(ratingService.getAverageRating(commentId));
    }
}
