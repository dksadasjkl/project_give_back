package com.project.give.service;

import com.project.give.entity.StoreReviewRating;
import com.project.give.repository.StoreReviewRatingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreReviewRatingService {

    @Autowired
    private StoreReviewRatingMapper ratingMapper;

    public void createRating(int userId, int commentId, int rating) {
        if (ratingMapper.existsRatingByUser(commentId, userId)) {
            throw new RuntimeException("이미 별점을 등록한 사용자입니다.");
        }

        StoreReviewRating newRating = StoreReviewRating.builder()
                .userId(userId)
                .commentId(commentId)
                .rating(rating)
                .build();

        ratingMapper.insertRating(newRating);
    }

    public double getAverageRating(int commentId) {
        return ratingMapper.selectAverageRatingByComment(commentId);
    }
}
