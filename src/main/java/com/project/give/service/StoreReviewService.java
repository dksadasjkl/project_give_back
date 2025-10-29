package com.project.give.service;

import com.project.give.dto.store.response.GetStoreReviewWithRatingResponseDto;
import com.project.give.repository.StoreReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreReviewService {

    @Autowired
    private StoreReviewMapper storeReviewMapper;

    // 💬 리뷰 + 평균별점 통합 조회
    public List<GetStoreReviewWithRatingResponseDto> getReviewsWithRatings(int productId) {
        return storeReviewMapper.selectReviewsWithRatings(productId);
    }
}