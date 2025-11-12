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
}
