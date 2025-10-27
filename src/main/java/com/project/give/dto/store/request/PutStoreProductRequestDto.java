package com.project.give.dto.store.request;

import com.project.give.entity.StoreProduct;
import lombok.Data;

@Data
public class PutStoreProductRequestDto {
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productOriginalPrice;
    private int productStock;
    private String productImageUrl;
    private boolean active;

    public StoreProduct toEntity(int productId) {
        return StoreProduct.builder()
                .productId(productId)
                .productName(productName)
                .productDescription(productDescription)
                .productPrice(productPrice)
                .productOriginalPrice(productOriginalPrice)
                .productStock(productStock)
                .productImageUrl(productImageUrl)
                .active(active)
                .build();
    }
}
