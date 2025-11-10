package com.project.give.service;

import com.project.give.entity.StoreReviewRating;
import com.project.give.repository.StoreReviewRatingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreReviewRatingService {

    @Autowired
    private StoreReviewRatingMapper ratingMapper;

    @Transactional
    public void createRating(int userId, int commentId, int rating) {
        boolean exists = ratingMapper.existsRatingByUser(commentId, userId);

        if (exists) {
            // 이미 등록된 경우 → UPDATE로 별점 수정
            ratingMapper.updateRating(commentId, userId, rating);
        } else {
            // 신규 등록
            StoreReviewRating newRating = StoreReviewRating.builder()
                    .userId(userId)
                    .commentId(commentId)
                    .rating(rating)
                    .build();
            ratingMapper.insertRating(newRating);
        }
    }

    public double getAverageRating(int commentId) {
        return ratingMapper.selectAverageRatingByComment(commentId);
    }
}
