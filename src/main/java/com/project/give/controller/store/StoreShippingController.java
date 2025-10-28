package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreShippingRequestDto;
import com.project.give.service.StoreShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/shipping")
public class StoreShippingController {

    @Autowired
    private StoreShippingService storeShippingService;

    //  배송 등록
    @PostMapping
    public ResponseEntity<?> createShipping(@RequestBody PostStoreShippingRequestDto dto) {
        storeShippingService.createShipping(dto);
        return ResponseEntity.ok("배송 정보가 등록되었습니다.");
    }

    //  주문별 배송 조회
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getShippingByOrder(@PathVariable int orderId) {
        return ResponseEntity.ok(storeShippingService.getShippingByOrder(orderId));
    }

    //  배송 상태 변경 (READY → IN_TRANSIT → DELIVERED)
    @PutMapping("/{shippingId}/status")
    public ResponseEntity<?> updateShippingStatus(@PathVariable int shippingId, @RequestParam String status) {
        storeShippingService.updateShippingStatus(shippingId, status);
        return ResponseEntity.ok("배송 상태가 변경되었습니다.");
    }
}