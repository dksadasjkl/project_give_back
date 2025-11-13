package com.project.give.repository;

import com.project.give.entity.StoreOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreOrderMapper {

    public int insertOrder(StoreOrder storeOrder);

    public List<StoreOrder> selectOrdersByUser(@Param("userId") int userId);

    public StoreOrder selectOrderById(@Param("orderId") int orderId, @Param("userId") int userId);

    public int updateOrderStatus(@Param("orderId") int orderId, @Param("userId") int userId, @Param("status") String status);

    List<StoreOrder> selectOrdersByUserPaged(@Param("userId") int userId, @Param("offset") int offset, @Param("size") int size);
    int countOrdersByUser(@Param("userId") int userId);
}