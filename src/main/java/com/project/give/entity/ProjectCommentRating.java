package com.project.give.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCommentRating {
    private int ratingId;
    private int commentId;
    private int userId;
    private int rating;
    private LocalDateTime createDate;
}