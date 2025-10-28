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