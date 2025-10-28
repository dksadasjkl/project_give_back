package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreCartResponseDto {
    private int cartId;
    private int userId;
    private int productId;
    private int quantity;
    private LocalDateTime createDate;
}
