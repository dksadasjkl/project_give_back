package com.project.give.repository;

import com.project.give.entity.ProjectCommentRating;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectCommentRatingMapper {
    public int insertRating(ProjectCommentRating rating);
    public Double selectAverageRatingByComment(@Param("commentId") int commentId);
    public boolean existsRatingByUser(@Param("commentId") int commentId, @Param("userId") int userId);
}