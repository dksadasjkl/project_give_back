package com.project.give.service.admin.store;

import com.project.give.dto.store.response.GetStoreReviewWithRatingResponseDto;
import com.project.give.entity.StoreReviewReport;
import com.project.give.repository.StoreReviewMapper;
import com.project.give.repository.StoreReviewRatingMapper;
import com.project.give.repository.StoreReviewReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminStoreReviewServiceImpl implements AdminStoreReviewService {

    @Autowired
    private StoreReviewMapper storeReviewMapper;

    @Autowired
    private StoreReviewRatingMapper ratingMapper;

    @Autowired
    private StoreReviewReportMapper reportMapper;

    @Override
    public Map<String, Object> getReviewList(int page, int size, Integer productId) {
        int offset = (page - 1) * size;

        List<GetStoreReviewWithRatingResponseDto> list =
                storeReviewMapper.selectReviewListAdmin(offset, size, productId);

        int total = storeReviewMapper.countReviewsAdmin(productId);

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    @Override
    public GetStoreReviewWithRatingResponseDto getReviewDetail(int commentId) {
        return storeReviewMapper.selectReviewDetailAdmin(commentId);
    }

    @Override
    public void deleteReview(int commentId) {
        // 별점 삭제
        ratingMapper.deleteRatingByCommentIdAdmin(commentId);
        // 리뷰 삭제
        storeReviewMapper.deleteReviewAdmin(commentId);
        // 신고 삭제
        reportMapper.deleteReportsByCommentIdAdmin(commentId);
    }

    @Override
    public Map<String, Object> getReviewReportList(int page, int size) {
        int offset = (page - 1) * size;

        List<StoreReviewReport> list = reportMapper.selectReviewReportsAdmin(offset, size);
        int total = reportMapper.countReviewReportsAdmin();

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    @Override
    public void resolveReviewReport(int reportId) {
        reportMapper.updateReportResolvedAdmin(reportId);
    }
}
