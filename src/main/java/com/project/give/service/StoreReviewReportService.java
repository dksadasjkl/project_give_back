package com.project.give.service;

import com.project.give.dto.store.request.PostStoreReviewReportRequestDto;
import com.project.give.entity.StoreReviewReport;
import com.project.give.repository.StoreReviewReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreReviewReportService {

    @Autowired
    private StoreReviewReportMapper storeReviewReportMapper;

    public void createReport(int reporterId, PostStoreReviewReportRequestDto dto) {
        // 중복 신고 방지
        int exists = storeReviewReportMapper.checkAlreadyReported(reporterId, dto.getCommentId());
        if (exists > 0) {
            throw new RuntimeException("이미 신고한 리뷰입니다.");
        }

        StoreReviewReport report = StoreReviewReport.toEntity(reporterId, dto);
        storeReviewReportMapper.insertReport(report);
    }
}