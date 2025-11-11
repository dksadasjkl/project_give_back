package com.project.give.repository;

import com.project.give.dto.store.response.GetStoreReviewWithRatingResponseDto;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreReviewMapper {

    // 💬 상품별 리뷰 + 평균 별점 + 유저 정보 (페이지 단위)
    List<GetStoreReviewWithRatingResponseDto> selectReviewsWithRatingsPaged(
            @Param("productId") int productId,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("orderBy") String orderBy
    );

    // ⭐ 평점 분포 (5~1점 카운트)
    @MapKey("rating")
    List<Map<String, Object>> selectRatingDistribution(@Param("productId") int productId);

    // 💬 전체 리뷰 개수
    int countReviewsByProduct(@Param("productId") int productId);
}
