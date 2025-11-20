package com.project.give.repository;

import com.project.give.entity.StoreReviewReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreReviewReportMapper {
   public int insertReport(StoreReviewReport report);
   public int checkAlreadyReported(@Param("reporterId") int reporterId, @Param("commentId") int commentId);

   // 관리자
   List<StoreReviewReport> selectReviewReportsAdmin(@Param("offset") int offset, @Param("size") int size);
   int countReviewReportsAdmin();
   int deleteReportsByCommentIdAdmin(@Param("commentId") int commentId);
   int updateReportResolvedAdmin(@Param("reportId") int reportId);
}
