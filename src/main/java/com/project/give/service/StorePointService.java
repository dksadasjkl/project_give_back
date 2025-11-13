package com.project.give.service;

import com.project.give.entity.StorePoint;
import com.project.give.repository.StorePointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorePointService {

    @Autowired
    private StorePointMapper storePointMapper;

    // 포인트 적립
    public void addPoint(int userId, int orderId, int amount) {
        int point = (int) (amount * 0.01); // 1% 적립
        StorePoint storePoint = StorePoint.builder()
                .userId(userId)
                .orderId(orderId)
                .pointAmount(point)
                .pointReason("구매 적립")
                .build();
        storePointMapper.insertPoint(storePoint);
    }

    // Service
    public Map<String, Object> getMyPointsPaged(int userId, int page, int size) {
        int offset = (page - 1) * size;
        List<StorePoint> points = storePointMapper.selectPointsByUserPaged(userId, offset, size);
        int totalCount = storePointMapper.countPointsByUser(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("points", points);
        result.put("totalCount", totalCount);
        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
        return result;
    }
}
