package com.project.give.service.admin.store;

import com.project.give.entity.StoreProductQna;
import java.util.Map;

public interface AdminStoreQnAService {

    // QnA 목록 조회
    Map<String, Object> getQnAList(int page, int size, Integer productId);

    // QnA 상세 조회
    StoreProductQna getQnADetail(int qnaId);

    // 관리자 답변 등록
    void answerQnA(int qnaId, String answer);

    // 관리자 답변 수정
    void updateAnswer(int qnaId, String answer);

    // QnA 삭제
    void deleteQnA(int qnaId);
}
