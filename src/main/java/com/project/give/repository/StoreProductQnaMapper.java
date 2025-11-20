package com.project.give.repository;

import com.project.give.entity.StoreProductQna;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreProductQnaMapper {

    public int insertQna(StoreProductQna qna);


    // ✅ 상품별 문의 조회 (페이지네이션)
    List<StoreProductQna> selectQnaByProductPaged(
            @Param("productId") int productId,
            @Param("offset") int offset,
            @Param("size") int size
    );

    // ✅ 상품별 문의 총 개수
    int countQnaByProduct(@Param("productId") int productId);

    public int updateAnswer(@Param("qnaId") int qnaId, @Param("answerContent") String answerContent);

    public int deleteQna(@Param("qnaId") int qnaId, @Param("userId") int userId);


    // ================= 관리자(Admin) =================

    // 목록
    List<StoreProductQna> selectQnAListAdmin(
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("productId") Integer productId
    );

    int countQnAAdmin(@Param("productId") Integer productId);

    // 상세
    StoreProductQna selectQnADetailAdmin(@Param("qnaId") int qnaId);

    // 답변 등록
    int insertAnswerAdmin(@Param("qnaId") int qnaId, @Param("answer") String answer);

    // 답변 수정
    int updateAnswerAdmin(@Param("qnaId") int qnaId, @Param("answer") String answer);

    // 삭제
    int deleteQnAAdmin(@Param("qnaId") int qnaId);
}
