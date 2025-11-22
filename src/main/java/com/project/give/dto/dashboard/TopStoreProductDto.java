package com.project.give.dto.dashboard;

import lombok.Data;

@Data
public class TopStoreProductDto {
    private int productId;
    private String productName;
    private String productImageUrl;
    private int productPrice;
}
