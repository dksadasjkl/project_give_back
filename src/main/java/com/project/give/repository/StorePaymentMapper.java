package com.project.give.repository;

import com.project.give.entity.StorePayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorePaymentMapper {

    public int insertPayment(StorePayment storePayment);

    public StorePayment selectPaymentByOrder(@Param("orderId") int orderId);

    public int updateOrderStatusToPaid(@Param("orderId") int orderId);
}
