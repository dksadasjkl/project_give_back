package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreOrderRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/orders")
public class StoreOrderController {

    @Autowired
    private StoreOrderService storeOrderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody PostStoreOrderRequestDto dto,
                                         @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUserId();
        dto.setUserId(userId);
        storeOrderService.createOrder(dto);
        return ResponseEntity.ok("주문 생성 완료");
    }

    // 주문 목록 조회 (내 주문)
    @GetMapping("/my")
    public ResponseEntity<?> getMyOrders(@AuthenticationPrincipal PrincipalUser principalUser) {
        int userId =  principalUser.getUserId();
        return ResponseEntity.ok(storeOrderService.getOrdersByUser(userId));
    }

    // 주문 상세 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable int orderId,
                                      @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId =  principalUser.getUserId();
        return ResponseEntity.ok(storeOrderService.getOrder(orderId, userId));
    }

    // 주문 상태 변경 (예: READY → IN_TRANSIT → DELIVERED → CANCELED)
    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int orderId,
                                               @RequestParam String status,
                                               @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId =  principalUser.getUserId();
        storeOrderService.updateOrderStatus(orderId, userId, status);
        return ResponseEntity.ok("주문 상태가 변경되었습니다.");
    }

    // 주문 취소 (사용자 요청)
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable int orderId,
                                         @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId =  principalUser.getUserId();
        storeOrderService.cancelOrder(orderId, userId);
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }
}