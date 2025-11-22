package com.project.give.entity;

import com.project.give.dto.store.response.GetStorePointResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorePoint {
    private int pointId;
    private int userId;
    private int orderId;
    private int pointAmount;
    private String pointReason;
    private LocalDateTime createDate;

    private String userUsername;
    private String userNickname;

    public GetStorePointResponseDto toResponseDto() {
        return GetStorePointResponseDto.builder()
                .pointId(pointId)
                .orderId(orderId)
                .pointAmount(pointAmount)
                .pointReason(pointReason)
                .createDate(createDate)
                .build();
    }
}