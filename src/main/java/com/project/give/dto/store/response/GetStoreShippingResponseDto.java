package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreShippingResponseDto {
    private int shippingId;
    private int orderId;
    private String recipientName;
    private String recipientPhone;
    private String address;
    private String zipcode;
    private String shippingCarrier;
    private String trackingNumber;
    private String shippingStatus;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;
}

