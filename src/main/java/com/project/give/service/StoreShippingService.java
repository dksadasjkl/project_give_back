package com.project.give.service;

import com.project.give.dto.store.request.PostStoreShippingRequestDto;
import com.project.give.dto.store.response.GetStoreShippingResponseDto;
import com.project.give.entity.StoreShipping;
import com.project.give.repository.StoreShippingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreShippingService {

    @Autowired
    private StoreShippingMapper storeShippingMapper;

    //  배송 등록
    public void createShipping(PostStoreShippingRequestDto dto) {
        storeShippingMapper.insertShipping(dto.toEntity());
    }

    //  주문별 배송 조회
    public GetStoreShippingResponseDto getShippingByOrder(int orderId) {
        StoreShipping shipping = storeShippingMapper.selectShippingByOrder(orderId);
        return shipping != null ? shipping.toResponseDto() : null;
    }
    //  사용자별 배송 조회 (마이페이지용)
    public List<GetStoreShippingResponseDto> getShippingByUser(int userId) {
        return storeShippingMapper.selectShippingByUser(userId)
                .stream()
                .map(StoreShipping::toResponseDto)
                .collect(Collectors.toList());
    }

    //  배송 상태 변경
    public void updateShippingStatus(int shippingId, String status) {
        storeShippingMapper.updateShippingStatus(shippingId, status);
    }

    // 🕒 자동 배송 상태 업데이트 (READY → IN_TRANSIT → DELIVERED)
    // - 매시간(정시마다) 스케줄러가 실행됨 (@Scheduled(cron = "0 0 * * * *"))
    // - READY 상태에서 6시간이 지나면 자동으로 IN_TRANSIT(배송 중)으로 변경
    // - IN_TRANSIT 상태에서 3일이 지나면 자동으로 DELIVERED(배송 완료)로 변경
    // - 배송 상태는 store_shipping_tb.shipping_status 컬럼 기준으로 관리됨
    @Scheduled(cron = "0 0 * * * *")
    public void autoUpdateShippingStatus() {
        // 📦 전체 배송 내역 조회
        List<StoreShipping> shippings = storeShippingMapper.selectAllShippings();

        for (StoreShipping shipping : shippings) {
            // 1️⃣ READY → IN_TRANSIT (6시간 이후)
            if ("READY".equals(shipping.getShippingStatus())
                    && shipping.getShippedAt().isBefore(LocalDateTime.now().minusHours(6))) {
                storeShippingMapper.updateShippingStatus(shipping.getShippingId(), "IN_TRANSIT");
            }
            // 2️⃣ IN_TRANSIT → DELIVERED (3일 이후)
            else if ("IN_TRANSIT".equals(shipping.getShippingStatus())
                    && shipping.getShippedAt().isBefore(LocalDateTime.now().minusDays(3))) {
                storeShippingMapper.updateShippingStatus(shipping.getShippingId(), "DELIVERED");
            }
        }
    }
}