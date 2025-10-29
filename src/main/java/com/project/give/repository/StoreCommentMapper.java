package com.project.give.repository;

import com.project.give.entity.StoreComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreCommentMapper {

    public int insertComment(StoreComment comment);

    public List<StoreComment> selectCommentsByProduct(@Param("productId") int productId);

    public StoreComment selectCommentById(@Param("commentId") int commentId);

    public int updateComment(@Param("commentId") int commentId,
                      @Param("userId") int userId,
                      @Param("commentText") String commentText);

    public int deleteComment(@Param("commentId") int commentId,
                      @Param("userId") int userId);

    public int countCommentsByProduct(@Param("productId") int productId);
}