package com.project.give.service;

import com.project.give.entity.StorePoint;
import com.project.give.repository.StorePointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorePointService {

    @Autowired
    private StorePointMapper storePointMapper;

    // 포인트 적립
    public void addPoint(int userId, int orderId, int amount) {
        int point = (int) (amount * 0.05); // 5% 적립
        StorePoint storePoint = StorePoint.builder()
                .userId(userId)
                .orderId(orderId)
                .pointAmount(point)
                .pointReason("구매 적립")
                .build();
        storePointMapper.insertPoint(storePoint);
    }

    // 사용자 포인트 내역 조회
    public List<StorePoint> getMyPoints(int userId) {
        return storePointMapper.selectPointsByUser(userId);
    }
}
