package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStorePaymentResponseDto {
    private int paymentId;
    private int orderId;
    private String paymentMethod;
    private String paymentStatus;
    private int amount;
    private LocalDateTime paidAt;
    private String transactionId;
}
