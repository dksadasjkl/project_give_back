package com.project.give.controller.admin.store;

import com.project.give.service.admin.store.AdminStorePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/store/payments")
public class AdminStorePaymentController {

    @Autowired
    private AdminStorePaymentService paymentService;

    // 전체 결제 목록 조회
    @GetMapping
    public ResponseEntity<?> getPaymentList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(paymentService.getPaymentList(page, size));
    }

    // 결제 상세 조회
    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPaymentDetail(@PathVariable int paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentDetail(paymentId));
    }

    // 결제 상태 변경 (PENDING / SUCCESS / FAILED)
    @PutMapping("/{paymentId}/status")
    public ResponseEntity<?> updatePaymentStatus(
            @PathVariable int paymentId,
            @RequestParam String status
    ) {
        paymentService.updatePaymentStatus(paymentId, status);
        return ResponseEntity.ok("결제 상태가 변경되었습니다.");
    }

    // 결제 삭제
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.ok("결제 내역이 삭제되었습니다.");
    }
}
