package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreWishlistResponseDto {
    private int wishlistId;
    private int productId;
    private LocalDateTime createDate;
}
