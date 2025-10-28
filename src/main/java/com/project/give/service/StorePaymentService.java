package com.project.give.service;

import com.project.give.dto.store.request.PostStorePaymentRequestDto;
import com.project.give.dto.store.response.GetStorePaymentResponseDto;
import com.project.give.entity.StorePayment;
import com.project.give.repository.StorePaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorePaymentService {

    @Autowired
    private StorePaymentMapper storePaymentMapper;

    @Autowired
    private StorePointService storePointService;

    //  결제 등록
    public void createPayment(int userId, PostStorePaymentRequestDto dto) {
        StorePayment payment = dto.toEntity();
        storePaymentMapper.insertPayment(payment);

        // 결제 성공이면 주문 상태 업데이트
        if ("SUCCESS".equals(payment.getPaymentStatus())) {
            storePaymentMapper.updateOrderStatusToPaid(payment.getOrderId());
            storePointService.addPoint(userId, payment.getOrderId(), payment.getAmount());
        }
    }

    //  특정 주문의 결제 조회
    public GetStorePaymentResponseDto getPaymentByOrder(int orderId) {
        StorePayment payment = storePaymentMapper.selectPaymentByOrder(orderId);
        return payment != null ? payment.toResponseDto() : null;
    }
}
