package com.project.give.dto.store.request;

import com.project.give.entity.StoreCart;
import lombok.Data;

@Data
public class PostStoreCartRequestDto {
    private int productId;
    private int quantity;

    public StoreCart toEntity(int userId) {
        return StoreCart.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}