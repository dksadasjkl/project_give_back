package com.project.give.repository;

import com.project.give.entity.StoreReviewRating;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreReviewRatingMapper {
    public int insertRating(StoreReviewRating rating);
    public Double selectAverageRatingByComment(@Param("commentId") int commentId);
    public boolean existsRatingByUser(@Param("commentId") int commentId, @Param("userId") int userId);
}