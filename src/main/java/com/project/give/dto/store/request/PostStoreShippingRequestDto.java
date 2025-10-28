package com.project.give.dto.store.request;

import com.project.give.entity.StoreShipping;
import lombok.Data;

@Data
public class PostStoreShippingRequestDto {
    private int orderId;
    private String recipientName;
    private String recipientPhone;
    private String address;
    private String zipcode;
    private String shippingCarrier;
    private String trackingNumber;

    public StoreShipping toEntity() {
        return StoreShipping.builder()
                .orderId(orderId)
                .recipientName(recipientName)
                .recipientPhone(recipientPhone)
                .address(address)
                .zipcode(zipcode)
                .shippingCarrier(shippingCarrier)
                .trackingNumber(trackingNumber)
                .shippingStatus("READY")
                .build();
    }
}
