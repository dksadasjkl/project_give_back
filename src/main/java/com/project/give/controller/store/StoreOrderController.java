package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreOrderRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/store/orders")
public class StoreOrderController {

    @Autowired
    private StoreOrderService storeOrderService;

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody PostStoreOrderRequestDto dto,
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        int userId = principalUser.getUserId();
        dto.setUserId(userId);

        int orderId = storeOrderService.createOrder(dto); //  생성된 주문번호 받기

        return ResponseEntity.ok(Map.of(
                "message", "주문 생성 완료",
                "orderId", orderId //  프론트에 전달
        ));
    }

    // 🔥 페이지네이션 버전
    @GetMapping
    public ResponseEntity<?> getMyStoreOrders(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(
                storeOrderService.getOrdersByUserPaged(principalUser.getUserId(), page, size)
        );
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

    // ✅ 구매 확정 (DELIVERED → CONFIRMED)
    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<?> confirmOrder(
            @PathVariable int orderId,
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        storeOrderService.confirmOrder(orderId, principalUser.getUserId());
        return ResponseEntity.ok("구매가 확정되었습니다.");
    }


}