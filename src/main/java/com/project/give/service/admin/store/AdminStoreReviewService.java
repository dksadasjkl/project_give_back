package com.project.give.service.admin.store;

import com.project.give.dto.store.response.GetStoreReviewWithRatingResponseDto;
import com.project.give.entity.StoreReviewReport;

import java.util.Map;

public interface AdminStoreReviewService {

    // 리뷰 목록
    Map<String, Object> getReviewList(int page, int size, Integer productId);

    // 리뷰 상세
    GetStoreReviewWithRatingResponseDto getReviewDetail(int commentId);

    // 리뷰 삭제
    void deleteReview(int commentId);

    // 신고 목록
    Map<String, Object> getReviewReportList(int page, int size);

    // 신고 처리 (resolved)
    void resolveReviewReport(int reportId);
}
