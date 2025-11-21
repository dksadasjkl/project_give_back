package com.project.give.entity;

import com.project.give.dto.store.request.PostStoreReviewReportRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreReviewReport {
    private int reportId;
    private int commentId;
    private int reporterId;
    private String reason;
    private LocalDateTime createDate;

    private String reporterUsername;
    private String commentText;
    private String productName;

    public static StoreReviewReport toEntity(int reporterId, PostStoreReviewReportRequestDto dto) {
        return StoreReviewReport.builder()
                .commentId(dto.getCommentId())
                .reporterId(reporterId)
                .reason(dto.getReason())
                .createDate(LocalDateTime.now())
                .build();
    }
}