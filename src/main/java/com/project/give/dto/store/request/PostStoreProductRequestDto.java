package com.project.give.dto.store.request;

import com.project.give.entity.StoreProduct;
import lombok.Data;

@Data
public class PostStoreProductRequestDto {
    private int categoryId;
    private int sellerId;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productOriginalPrice;
    private int productStock;
    private String productImageUrl;

    public StoreProduct toEntity() {
        return StoreProduct.builder()
                .categoryId(categoryId)
                .sellerId(sellerId)
                .productName(productName)
                .productDescription(productDescription)
                .productPrice(productPrice)
                .productOriginalPrice(productOriginalPrice)
                .productStock(productStock)
                .productImageUrl(productImageUrl)
                .active(true)
                .build();
    }
}