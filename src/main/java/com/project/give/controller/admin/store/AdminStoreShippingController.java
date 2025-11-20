package com.project.give.controller.admin.store;

import com.project.give.service.admin.store.AdminStoreShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/store/shipping")
public class AdminStoreShippingController {

    @Autowired
    private AdminStoreShippingService shippingService;

    // 전체 배송 목록
    @GetMapping
    public ResponseEntity<?> getShippingList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(shippingService.getShippingList(page, size));
    }

    // 배송 상세 조회
    @GetMapping("/{shippingId}")
    public ResponseEntity<?> getShippingDetail(@PathVariable int shippingId) {
        return ResponseEntity.ok(shippingService.getShippingDetail(shippingId));
    }

    // 배송 상태 변경
    @PutMapping("/{shippingId}/status")
    public ResponseEntity<?> updateShippingStatus(
            @PathVariable int shippingId,
            @RequestParam String status
    ) {
        shippingService.updateShippingStatus(shippingId, status);
        return ResponseEntity.ok("배송 상태가 변경되었습니다.");
    }

    // 운송장 번호 수정
    @PutMapping("/{shippingId}/tracking")
    public ResponseEntity<?> updateTrackingNumber(
            @PathVariable int shippingId,
            @RequestParam String trackingNumber
    ) {
        shippingService.updateTrackingNumber(shippingId, trackingNumber);
        return ResponseEntity.ok("운송장 번호가 수정되었습니다.");
    }

    // 배송 삭제
    @DeleteMapping("/{shippingId}")
    public ResponseEntity<?> deleteShipping(@PathVariable int shippingId) {
        shippingService.deleteShipping(shippingId);
        return ResponseEntity.ok("배송 정보가 삭제되었습니다.");
    }
}
