package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreCommentRequestDto;
import com.project.give.dto.store.request.PutStoreCommentRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/comments")
public class StoreCommentController {

    @Autowired
    private StoreCommentService storeCommentService;

    // 💬 댓글 작성 (로그인 유저만 가능)
    @PostMapping
    public ResponseEntity<?> createComment(
            @RequestBody PostStoreCommentRequestDto dto,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUserId();
        storeCommentService.createComment(userId, dto);
        return ResponseEntity.ok("댓글 등록 완료");
    }

    // 💬 상품별 댓글 목록 조회
    @GetMapping("/{productId}")
    public ResponseEntity<?> getComments(@PathVariable int productId) {
        return ResponseEntity.ok(storeCommentService.getCommentsByProduct(productId));
    }

    // 💬 댓글 수정 (본인만 가능)
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable int commentId,
            @RequestBody PutStoreCommentRequestDto dto,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUserId();
        storeCommentService.updateComment(commentId, userId, dto);
        return ResponseEntity.ok("댓글 수정 완료");
    }

    // 💬 댓글 삭제 (본인만 가능)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable int commentId,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUserId();
        storeCommentService.deleteComment(commentId, userId);
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    // 💬 상품별 댓글 개수
    @GetMapping("/{productId}/count")
    public ResponseEntity<?> countComments(@PathVariable int productId) {
        return ResponseEntity.ok(storeCommentService.countComments(productId));
    }
}
