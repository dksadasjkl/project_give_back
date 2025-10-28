package com.project.give.dto.store.request;

import com.project.give.entity.StoreOrder;
import lombok.Data;

@Data
public class PostStoreOrderRequestDto {
    private int userId;
    private int productId;
    private int quantity;
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
}