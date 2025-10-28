package com.project.give.dto.store.request;

import com.project.give.entity.StorePayment;
import lombok.Data;

@Data
public class PostStorePaymentRequestDto {
    private int orderId;
    private String paymentMethod;
    private String paymentStatus; // PENDING / SUCCESS / FAILED
    private int amount;
    private String transactionId;

    public StorePayment toEntity() {
        return StorePayment.builder()
                .orderId(orderId)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentStatus)
                .amount(amount)
                .transactionId(transactionId)
                .build();
    }
}
