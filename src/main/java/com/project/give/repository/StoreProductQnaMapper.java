package com.project.give.repository;

import com.project.give.entity.StoreProductQna;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreProductQnaMapper {

    public int insertQna(StoreProductQna qna);

    public List<StoreProductQna> selectQnaByProduct(@Param("productId") int productId);

    public int updateAnswer(@Param("qnaId") int qnaId, @Param("answerContent") String answerContent);

    public int deleteQna(@Param("qnaId") int qnaId, @Param("userId") int userId);
}
