package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreOrderResponseDto {
    private int orderId;
    private int userId;
    private int productId;
    private int quantity;
    private int totalAmount;
    private String orderStatus;
    private String orderStatusText;
    private LocalDateTime orderDate;
}