package com.project.give.controller.admin.store;

import com.project.give.service.admin.store.AdminStoreQnAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/store/qna")
public class AdminStoreQnAController {

    @Autowired
    private AdminStoreQnAService qnaService;

    // 전체 QnA 목록
    @GetMapping
    public ResponseEntity<?> getQnAList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer productId
    ) {
        return ResponseEntity.ok(qnaService.getQnAList(page, size, productId));
    }

    // QnA 상세
    @GetMapping("/{qnaId}")
    public ResponseEntity<?> getQnADetail(@PathVariable int qnaId) {
        return ResponseEntity.ok(qnaService.getQnADetail(qnaId));
    }

    // 답변 등록
    @PostMapping("/{qnaId}/answer")
    public ResponseEntity<?> answerQnA(
            @PathVariable int qnaId,
            @RequestParam String answer
    ) {
        qnaService.answerQnA(qnaId, answer);
        return ResponseEntity.ok("답변이 등록되었습니다.");
    }

    // 답변 수정
    @PutMapping("/{qnaId}/answer")
    public ResponseEntity<?> updateAnswer(
            @PathVariable int qnaId,
            @RequestParam String answer
    ) {
        qnaService.updateAnswer(qnaId, answer);
        return ResponseEntity.ok("답변이 수정되었습니다.");
    }

    // 삭제
    @DeleteMapping("/{qnaId}")
    public ResponseEntity<?> deleteQnA(@PathVariable int qnaId) {
        qnaService.deleteQnA(qnaId);
        return ResponseEntity.ok("문의가 삭제되었습니다.");
    }
}

