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

    // 상품 정보
    private String productName;
    private String productImageUrl;

    // 결제 정보
    private String paymentMethod;
    private String paymentStatus;
    private int paymentAmount;

    // 배송 정보
    private String recipientName;
    private String address;
    private String shippingCarrier;
    private String trackingNumber;
    private String shippingStatus;
}