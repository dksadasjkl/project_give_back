package com.project.give.dto.store.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStorePointResponseDto {
    private int pointId;
    private int orderId;
    private int pointAmount;
    private String pointReason;
    private LocalDateTime createDate;
}
