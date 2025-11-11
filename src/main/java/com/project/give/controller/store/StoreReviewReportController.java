package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreReviewReportRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreReviewReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/review")
public class StoreReviewReportController {

    @Autowired
    private StoreReviewReportService storeReviewReportService;

    @PostMapping("/report")
    public ResponseEntity<?> reportReview(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody PostStoreReviewReportRequestDto dto) {
        try {
            storeReviewReportService.createReport(principalUser.getUserId(), dto);
            return ResponseEntity.ok("리뷰 신고가 접수되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
