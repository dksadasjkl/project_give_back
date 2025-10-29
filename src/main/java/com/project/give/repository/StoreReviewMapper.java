package com.project.give.repository;

import com.project.give.dto.store.response.GetStoreReviewWithRatingResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreReviewMapper {

    // 상품별 리뷰 + 평균 별점 조회
    public List<GetStoreReviewWithRatingResponseDto> selectReviewsWithRatings(@Param("productId") int productId);
}
