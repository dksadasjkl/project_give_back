package com.project.give.service;

import com.project.give.entity.ProjectCommentRating;
import com.project.give.repository.ProjectCommentRatingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectCommentRatingService {

    @Autowired
    private ProjectCommentRatingMapper ratingMapper;

    public void createRating(int userId, int commentId, int rating) {
        if (ratingMapper.existsRatingByUser(commentId, userId)) {
            throw new RuntimeException("이미 별점을 등록한 사용자입니다.");
        }

        ProjectCommentRating newRating = ProjectCommentRating.builder()
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
