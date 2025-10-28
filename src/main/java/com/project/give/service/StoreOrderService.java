package com.project.give.service;

import com.project.give.dto.store.request.PostStoreOrderRequestDto;
import com.project.give.dto.store.response.GetStoreOrderResponseDto;
import com.project.give.entity.StoreOrder;
import com.project.give.repository.StoreOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreOrderService {

    @Autowired
    private StoreOrderMapper storeOrderMapper;

    // ✅ 주문 생성
    public void createOrder(PostStoreOrderRequestDto dto) {
        StoreOrder order = dto.toEntity();
        storeOrderMapper.insertOrder(order);
    }

    // ✅ 내 주문 목록 조회
    public List<GetStoreOrderResponseDto> getOrdersByUser(int userId) {
        return storeOrderMapper.selectOrdersByUser(userId)
                .stream().map(StoreOrder::toResponseDto)
                .collect(Collectors.toList());
    }

    // ✅ 주문 상세 조회
    public GetStoreOrderResponseDto getOrder(int orderId, int userId) {
        StoreOrder order = storeOrderMapper.selectOrderById(orderId, userId);
        return order != null ? order.toResponseDto() : null;
    }

    // ✅ 주문 상태 변경
    public void updateOrderStatus(int orderId, int userId, String status) {
        storeOrderMapper.updateOrderStatus(orderId, userId, status);
    }

    // ✅ Service
    public void cancelOrder(int orderId, int userId) {
        // 상태만 변경
        int result = storeOrderMapper.updateOrderStatus(orderId, userId, "CANCELLED");
        if (result == 0) throw new RuntimeException("주문 취소 실패 또는 권한 없음");
    }
}
