package com.project.give.entity;

import com.project.give.dto.store.response.GetStoreProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProduct {
    private int productId;
    private int categoryId;
    private int sellerId;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productOriginalPrice;
    private int productStock;
    private String productImageUrl;
    private boolean active;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public GetStoreProductResponseDto toResponseDto() {
        return GetStoreProductResponseDto.builder()
                .productId(productId)
                .productName(productName)
                .productDescription(productDescription)
                .productPrice(productPrice)
                .productOriginalPrice(productOriginalPrice)
                .productStock(productStock)
                .productImageUrl(productImageUrl)
                .active(active)
                .createDate(createDate)
                .build();
    }
}