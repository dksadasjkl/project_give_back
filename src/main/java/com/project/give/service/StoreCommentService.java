package com.project.give.service;

import com.project.give.dto.store.request.PostStoreCommentRequestDto;
import com.project.give.dto.store.request.PutStoreCommentRequestDto;
import com.project.give.dto.store.response.GetStoreCommentResponseDto;
import com.project.give.entity.StoreComment;
import com.project.give.repository.StoreCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreCommentService {

    @Autowired
    private StoreCommentMapper storeCommentMapper;

    // 💬 댓글 등록
    public void createComment(int userId, PostStoreCommentRequestDto dto) {
        StoreComment comment = dto.toEntity(userId);
        int result = storeCommentMapper.insertComment(comment);
        if (result == 0) throw new RuntimeException("댓글 등록 실패");
    }

    // 💬 상품별 댓글 조회
    public List<GetStoreCommentResponseDto> getCommentsByProduct(int productId) {
        return storeCommentMapper.selectCommentsByProduct(productId)
                .stream()
                .map(StoreComment::toResponseDto)
                .collect(Collectors.toList());
    }

    // 💬 댓글 수정
    public void updateComment(int commentId, int userId, PutStoreCommentRequestDto dto) {
        int result = storeCommentMapper.updateComment(commentId, userId, dto.getCommentText());
        if (result == 0) throw new RuntimeException("댓글 수정 실패 또는 권한 없음");
    }

    // 💬 댓글 삭제
    public void deleteComment(int commentId, int userId) {
        int result = storeCommentMapper.deleteComment(commentId, userId);
        if (result == 0) throw new RuntimeException("댓글 삭제 실패 또는 권한 없음");
    }

    // 💬 댓글 개수
    public int countComments(int productId) {
        return storeCommentMapper.countCommentsByProduct(productId);
    }
}
