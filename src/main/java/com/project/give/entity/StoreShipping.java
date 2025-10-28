package com.project.give.entity;

import com.project.give.dto.store.response.GetStoreShippingResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreShipping {
    private int shippingId;
    private int orderId;
    private String recipientName;
    private String recipientPhone;
    private String address;
    private String zipcode;
    private String shippingCarrier;
    private String trackingNumber;
    private String shippingStatus; // READY, IN_TRANSIT, DELIVERED
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;

    public GetStoreShippingResponseDto toResponseDto() {
        return GetStoreShippingResponseDto.builder()
                .shippingId(shippingId)
                .orderId(orderId)
                .recipientName(recipientName)
                .recipientPhone(recipientPhone)
                .address(address)
                .zipcode(zipcode)
                .shippingCarrier(shippingCarrier)
                .trackingNumber(trackingNumber)
                .shippingStatus(shippingStatus)
                .shippedAt(shippedAt)
                .deliveredAt(deliveredAt)
                .build();
    }
}
