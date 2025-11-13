package com.project.give.service;

import com.project.give.dto.store.request.PostStoreOrderRequestDto;
import com.project.give.dto.store.response.GetStoreOrderResponseDto;
import com.project.give.entity.StoreOrder;
import com.project.give.repository.StoreOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreOrderService {

    @Autowired
    private StoreOrderMapper storeOrderMapper;

//    // 주문 생성
//    @Transactional
//    public int createOrder(PostStoreOrderRequestDto dto) {
//        StoreOrder order = dto.toEntity();
//        int result = storeOrderMapper.insertOrder(order);
//        if (result == 0) {
//            throw new RuntimeException("주문 생성 실패");
//        }
//        return order.getOrderId(); // ✅ useGeneratedKeys 로 세팅된 값 반환
//    }

    @Transactional
    public List<Integer> createOrders(PostStoreOrderRequestDto dto) {

        // 1) 장바구니 결제
        if (dto.getItems() != null && !dto.getItems().isEmpty()) {

            List<Integer> orderIds = new ArrayList<>();

            for (PostStoreOrderRequestDto.OrderItem item : dto.getItems()) {
                StoreOrder order = StoreOrder.builder()
                        .userId(dto.getUserId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .totalAmount(dto.getTotalAmount())
                        .orderStatus("READY")
                        .build();

                storeOrderMapper.insertOrder(order);
                orderIds.add(order.getOrderId());
            }

            return orderIds;
        }

        // 2) 일반 결제
        StoreOrder order = dto.toEntity();
        storeOrderMapper.insertOrder(order);

        return List.of(order.getOrderId());
    }

    // 내 주문 목록 조회
    public List<GetStoreOrderResponseDto> getOrdersByUser(int userId) {
        return storeOrderMapper.selectOrdersByUser(userId)
                .stream().map(StoreOrder::toResponseDto)
                .collect(Collectors.toList());
    }

    // 주문 상세 조회
    public GetStoreOrderResponseDto getOrder(int orderId, int userId) {
        StoreOrder order = storeOrderMapper.selectOrderById(orderId, userId);
        return order != null ? order.toResponseDto() : null;
    }

    // 주문 상태 변경
    public void updateOrderStatus(int orderId, int userId, String status) {
        storeOrderMapper.updateOrderStatus(orderId, userId, status);
    }


    public void cancelOrder(int orderId, int userId) {
        // 상태만 변경
        int result = storeOrderMapper.updateOrderStatus(orderId, userId, "CANCELLED");
        if (result == 0) throw new RuntimeException("주문 취소 실패 또는 권한 없음");
    }

    // ✅ 구매 확정 (배송 완료된 주문만 가능)
    @Transactional
    public void confirmOrder(int orderId, int userId) {
        StoreOrder order = storeOrderMapper.selectOrderById(orderId, userId);
        if (order == null) throw new RuntimeException("주문을 찾을 수 없습니다.");
        if (!"DELIVERED".equals(order.getOrderStatus())) {
            throw new RuntimeException("배송 완료 상태에서만 구매 확정이 가능합니다.");
        }
        storeOrderMapper.updateOrderStatus(orderId, userId, "CONFIRMED");
    }

    public Map<String, Object> getOrdersByUserPaged(int userId, int page, int size) {
        int offset = (page - 1) * size;
        List<StoreOrder> orders = storeOrderMapper.selectOrdersByUserPaged(userId, offset, size);
        int totalCount = storeOrderMapper.countOrdersByUser(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("orders", orders.stream().map(StoreOrder::toResponseDto).collect(Collectors.toList()));
        result.put("totalCount", totalCount);
        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
        return result;
    }


}
