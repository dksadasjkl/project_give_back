package com.project.give.entity;

import com.project.give.dto.store.response.GetStorePaymentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorePayment {
    private int paymentId;
    private int orderId;
    private String paymentMethod;
    private String paymentStatus; // PENDING, SUCCESS, FAILED
    private int amount;
    private LocalDateTime paidAt;
    private String transactionId;

    public GetStorePaymentResponseDto toResponseDto() {
        return GetStorePaymentResponseDto.builder()
                .paymentId(paymentId)
                .orderId(orderId)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentStatus)
                .amount(amount)
                .paidAt(paidAt)
                .transactionId(transactionId)
                .build();
    }
}