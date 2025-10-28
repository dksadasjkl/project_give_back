package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStorePaymentRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.service.StorePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/payments")
public class StorePaymentController {

    @Autowired
    private StorePaymentService storePaymentService;

    //  결제 등록
    @PostMapping
    public ResponseEntity<?> createPayment(
            @RequestBody PostStorePaymentRequestDto dto,
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        storePaymentService.createPayment(principalUser.getUserId(), dto);
        return ResponseEntity.ok("결제 내역이 저장되었습니다.");
    }

    //  특정 주문의 결제 내역 조회
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getPaymentByOrder(@PathVariable int orderId) {
        return ResponseEntity.ok(storePaymentService.getPaymentByOrder(orderId));
    }
}
