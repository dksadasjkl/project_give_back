package com.project.give.entity;

import com.project.give.dto.store.response.GetStoreCartResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreCart {
    private int cartId;
    private int userId;
    private int productId;
    private int quantity;
    private LocalDateTime createDate;

    private String productName;
    private Integer productPrice;
    private String productImageUrl;

    public GetStoreCartResponseDto toResponseDto() {
        return GetStoreCartResponseDto.builder()
                .cartId(cartId)
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .createDate(createDate)
                .productName(productName)
                .productPrice(productPrice)
                .productImageUrl(productImageUrl)
                .build();
    }
}
