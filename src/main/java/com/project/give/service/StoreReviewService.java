package com.project.give.service;

import com.project.give.dto.store.response.GetStoreReviewWithRatingResponseDto;
import com.project.give.repository.StoreReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreReviewService {

    @Autowired
    private StoreReviewMapper storeReviewMapper;

    /**
     * 💬 상품 리뷰, 평균별점, 평점 분포 종합 조회
     */
    public Map<String, Object> getReviewsWithRatings(int productId, int page, int size, String sort) {

        int offset = (page - 1) * size;

        // 💬 정렬 기준
        String orderBy = "sct.create_date DESC";
        if ("ratingHigh".equals(sort)) orderBy = "average_rating DESC";
        else if ("ratingLow".equals(sort)) orderBy = "average_rating ASC";

        // 페이지 단위 리뷰 목록 조회
        List<GetStoreReviewWithRatingResponseDto> reviews =
                storeReviewMapper.selectReviewsWithRatingsPaged(productId, offset, size, orderBy);

        // 전체 리뷰 수
        int totalCount = storeReviewMapper.countReviewsByProduct(productId);

        // 평점 분포 (5~1점)
        List<Map<String, Object>> distribution =
                storeReviewMapper.selectRatingDistribution(productId);

        // 전체 평균 계산
        double avg = reviews.stream()
                .mapToDouble(GetStoreReviewWithRatingResponseDto::getAverageRating)
                .average()
                .orElse(0.0);

        // 응답 데이터 구성
        Map<String, Object> result = new HashMap<>();
        result.put("reviews", reviews);
        result.put("averageRating", Math.round(avg * 10) / 10.0);
        result.put("distribution", distribution);
        result.put("totalCount", totalCount);
        result.put("currentPage", page);
        result.put("pageSize", size);

        return result;
    }

    /**
     *  리뷰 작성 자격 확인 (상품 구매 여부)
     */
    public boolean checkReviewEligibility(int userId, int productId) {
        return storeReviewMapper.existsOrderByUserAndProduct(userId, productId);
    }
}
