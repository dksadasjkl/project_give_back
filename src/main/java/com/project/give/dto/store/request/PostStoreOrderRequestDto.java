package com.project.give.dto.store.request;

import com.project.give.entity.StoreOrder;
import lombok.Data;

import java.util.List;

@Data
public class PostStoreOrderRequestDto {
    private int userId;

    // 일반 결제
    private Integer productId;
    private Integer quantity;

    // 장바구니 결제
    private List<OrderItem> items;
    private int totalAmount;

    public StoreOrder toEntity() {
        return StoreOrder.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .totalAmount(totalAmount)
                .orderStatus("READY")
                .build();
    }

    @Data
    public static class OrderItem {
        private int productId;
        private int quantity;
    }
}