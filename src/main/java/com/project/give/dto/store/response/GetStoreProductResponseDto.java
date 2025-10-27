package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreProductResponseDto {
    private int productId;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productOriginalPrice;
    private int productStock;
    private String productImageUrl;
    private boolean active;
    private LocalDateTime createDate;
}