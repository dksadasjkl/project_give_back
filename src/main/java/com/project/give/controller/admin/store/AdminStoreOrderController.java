package com.project.give.controller.admin.store;

import com.project.give.service.admin.store.AdminStoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/store/orders")
public class AdminStoreOrderController {

    @Autowired
    private AdminStoreOrderService adminStoreOrderService;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(adminStoreOrderService.getOrderList(page, size));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> detail(@PathVariable int orderId) {
        return ResponseEntity.ok(adminStoreOrderService.getOrderDetail(orderId));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable int orderId,
            @RequestParam String status
    ) {
        adminStoreOrderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("주문 상태가 변경되었습니다.");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> delete(@PathVariable int orderId) {
        adminStoreOrderService.deleteOrder(orderId);
        return ResponseEntity.ok("주문이 삭제되었습니다.");
    }
}

