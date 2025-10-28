package com.project.give.dto.store.request;

import com.project.give.entity.StorePoint;
import lombok.Data;

@Data
public class PostStorePointRequestDto {
    private int orderId;
    private int pointAmount;
    private String pointReason;

    public StorePoint toEntity(int userId) {
        return StorePoint.builder()
                .userId(userId)
                .orderId(orderId)
                .pointAmount(pointAmount)
                .pointReason(pointReason)
                .build();
    }
}
