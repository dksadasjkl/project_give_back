package com.project.give.repository;

import com.project.give.entity.StoreShipping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreShippingMapper {

    public int insertShipping(StoreShipping storeShipping);

    public StoreShipping selectShippingByOrder(@Param("orderId") int orderId);

    List<StoreShipping> selectShippingByUser(@Param("userId") int userId);

    List<StoreShipping> selectAllShippings();

    public int updateShippingStatus(@Param("shippingId") int shippingId, @Param("status") String status);

    // 관리자

    // 전체 배송 목록 (페이징)
    List<StoreShipping> selectShippingListAdmin(
            @Param("offset") int offset,
            @Param("size") int size
    );

    // 전체 배송 개수
    int countAllShippingAdmin();

    // 배송 상세 조회
    StoreShipping selectShippingDetailAdmin(@Param("shippingId") int shippingId);

    // 운송장 번호 변경
    int updateTrackingNumberAdmin(
            @Param("shippingId") int shippingId,
            @Param("trackingNumber") String trackingNumber
    );

    // 관리자 삭제
    int deleteShippingAdmin(@Param("shippingId") int shippingId);

    int updateShippingStatusAdmin(
            @Param("shippingId") int shippingId,
            @Param("status") String status
    );
}
