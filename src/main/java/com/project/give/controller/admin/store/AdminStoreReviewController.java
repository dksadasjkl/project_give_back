package com.project.give.controller.admin.store;

import com.project.give.service.admin.store.AdminStoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/store/reviews")
public class AdminStoreReviewController {

    @Autowired
    private AdminStoreReviewService reviewService;

    // 전체 리뷰 목록
    @GetMapping
    public ResponseEntity<?> getReviewList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer productId
    ) {
        return ResponseEntity.ok(reviewService.getReviewList(page, size, productId));
    }

    // 리뷰 상세
    @GetMapping("/{commentId}")
    public ResponseEntity<?> getReviewDetail(@PathVariable int commentId) {
        return ResponseEntity.ok(reviewService.getReviewDetail(commentId));
    }

    // 리뷰 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteReview(@PathVariable int commentId) {
        reviewService.deleteReview(commentId);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }

    // 신고 목록
    @GetMapping("/reports")
    public ResponseEntity<?> getReviewReports(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(reviewService.getReviewReportList(page, size));
    }

    // 신고 처리
    @PutMapping("/reports/{reportId}/resolve")
    public ResponseEntity<?> resolveReviewReport(@PathVariable int reportId) {
        reviewService.resolveReviewReport(reportId);
        return ResponseEntity.ok("신고가 처리되었습니다.");
    }
}
