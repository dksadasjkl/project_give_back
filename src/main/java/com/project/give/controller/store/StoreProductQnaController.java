package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreProductQnaRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreProductQnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/qna")
public class StoreProductQnaController {

    @Autowired
    private StoreProductQnaService storeProductQnaService;

    // 문의 등록
    @PostMapping
    public ResponseEntity<?> createQna(@AuthenticationPrincipal PrincipalUser principalUser,
                                       @RequestBody PostStoreProductQnaRequestDto dto) {
        storeProductQnaService.createQna(principalUser.getUserId(), dto);
        return ResponseEntity.ok("상품 문의가 등록되었습니다.");
    }

    // 상품별 문의 조회
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getQnaByProduct(@PathVariable int productId) {
        return ResponseEntity.ok(storeProductQnaService.getQnaByProduct(productId));
    }

    // 관리자 답변 등록
    @PutMapping("/{qnaId}/answer")
    public ResponseEntity<?> answerQna(@PathVariable int qnaId, @RequestParam String answerContent) {
        try {
            storeProductQnaService.answerQna(qnaId, answerContent);
            return ResponseEntity.ok("답변이 등록되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 문의 삭제
    @DeleteMapping("/{qnaId}")
    public ResponseEntity<?> deleteQna(@AuthenticationPrincipal PrincipalUser principalUser,
                                       @PathVariable int qnaId) {
        try {
            storeProductQnaService.deleteQna(qnaId, principalUser.getUserId());
            return ResponseEntity.ok("상품 문의가 삭제되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}