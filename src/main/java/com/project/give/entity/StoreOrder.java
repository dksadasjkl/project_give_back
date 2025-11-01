package com.project.give.entity;

import com.project.give.dto.store.response.GetStoreOrderResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrder {
    private int orderId;
    private int userId;
    private int productId;
    private int quantity;
    private int totalAmount;
    private String orderStatus; // READY / IN_TRANSIT / DELIVERED / CANCELLED
    private LocalDateTime orderDate;


    // 상품 정보
    private String productName;
    private String productImageUrl;

    // ✅ 결제 정보
    private String paymentMethod;   // KAKAO_PAY 등
    private String paymentStatus;   // SUCCESS / FAIL
    private int paymentAmount;

    // ✅ 배송 정보
    private String recipientName;
    private String address;
    private String shippingCarrier; // CJ대한통운
    private String trackingNumber;  // C1234567890
    private String shippingStatus;  // READY / IN_TRANSIT / DELIVERED

    // ✅ DTO 변환 메서드
    public GetStoreOrderResponseDto toResponseDto() {
        return GetStoreOrderResponseDto.builder()
                .orderId(orderId)
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .totalAmount(totalAmount)
                .orderStatus(orderStatus)
                .orderStatusText(convertStatusText(orderStatus))
                .orderDate(orderDate)
                .productName(productName)
                .productImageUrl(productImageUrl)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentStatus)
                .paymentAmount(paymentAmount)
                .recipientName(recipientName)
                .address(address)
                .shippingCarrier(shippingCarrier)
                .trackingNumber(trackingNumber)
                .shippingStatus(shippingStatus)
                .build();
    }
    private String convertStatusText(String status) {
        switch (status) {
            case "READY":
                return "주문 확인 중";
            case "IN_TRANSIT":
                return "배송 중";
            case "DELIVERED":
                return "배송 완료";
            case "CANCELLED":
                return "주문 취소";
            default:
                return "알 수 없음";
        }
    }
}