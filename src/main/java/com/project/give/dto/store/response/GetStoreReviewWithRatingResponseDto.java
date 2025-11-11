package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreReviewWithRatingResponseDto {
    private int commentId;       // 리뷰 ID
    private int productId;       // 상품 ID
    private int userId;          // 작성자 ID
    private String username;     // 작성자 아이디 (또는 닉네임)
    private String profileImageUrl;
    private String commentText;  // 리뷰 내용
    private LocalDateTime createDate; // 작성일
    private LocalDateTime updateDate; // 수정일
    private double averageRating;     // 리뷰 별점 평균
    private int ratingCount;          // 해당 리뷰의 별점 참여 수
}
