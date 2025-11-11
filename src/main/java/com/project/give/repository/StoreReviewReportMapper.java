package com.project.give.repository;

import com.project.give.entity.StoreReviewReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreReviewReportMapper {
   public int insertReport(StoreReviewReport report);
   public int checkAlreadyReported(@Param("reporterId") int reporterId, @Param("commentId") int commentId);
}
