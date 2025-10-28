package com.project.give.service;

import com.project.give.dto.store.request.PostStoreProductQnaRequestDto;
import com.project.give.dto.store.response.GetStoreProductQnaResponseDto;
import com.project.give.entity.StoreProductQna;
import com.project.give.repository.StoreProductQnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreProductQnaService {

    @Autowired
    private StoreProductQnaMapper storeProductQnaMapper;

    // 문의 등록
    public void createQna(int userId, PostStoreProductQnaRequestDto dto) {
        storeProductQnaMapper.insertQna(dto.toEntity(userId));
    }

    // 상품별 문의 조회
    public List<GetStoreProductQnaResponseDto> getQnaByProduct(int productId) {
        return storeProductQnaMapper.selectQnaByProduct(productId)
                .stream()
                .map(StoreProductQna::toResponseDto)
                .collect(Collectors.toList());
    }

    public void answerQna(int qnaId, String answerContent) {
        int result = storeProductQnaMapper.updateAnswer(qnaId, answerContent);
        if (result == 0) {
            throw new RuntimeException("해당 문의를 찾을 수 없습니다.");
        }
    }

    public void deleteQna(int qnaId, int userId) {
        int result = storeProductQnaMapper.deleteQna(qnaId, userId);
        if (result == 0) {
            throw new RuntimeException("해당 문의를 찾을 수 없습니다.");
        }
    }

}
