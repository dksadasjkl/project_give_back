package com.project.give.entity;

import com.project.give.dto.store.response.GetStoreWishlistResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreWishlist {
    private int wishlistId;
    private int userId;
    private int productId;
    private LocalDateTime createDate;

    private String productName;
    private Integer productPrice;
    private String productImageUrl;

    public GetStoreWishlistResponseDto toResponseDto() {
        return GetStoreWishlistResponseDto.builder()
                .wishlistId(wishlistId)
                .productId(productId)
                .createDate(createDate)
                .productName(productName)
                .productPrice(productPrice)
                .productImageUrl(productImageUrl)
                .build();
    }
}