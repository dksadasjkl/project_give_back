package com.project.give.repository;

import com.project.give.entity.StorePayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorePaymentMapper {

    public int insertPayment(StorePayment storePayment);

    public StorePayment selectPaymentByOrder(@Param("orderId") int orderId);

    public int updateOrderStatusToPaid(@Param("orderId") int orderId);


    // 관리자(Admin)

    // 전체 결제 목록 (페이징)
    List<StorePayment> selectPaymentsPagedAdmin(
            @Param("offset") int offset,
            @Param("size") int size
    );

    // 전체 결제 개수
    int countAllPaymentsAdmin();

    // 결제 상세
    StorePayment selectPaymentDetailAdmin(@Param("paymentId") int paymentId);

    // 결제 상태 변경
    int updatePaymentStatusAdmin(
            @Param("paymentId") int paymentId,
            @Param("status") String status
    );

    // 관리자 삭제
    int deletePaymentAdmin(@Param("paymentId") int paymentId);
}
