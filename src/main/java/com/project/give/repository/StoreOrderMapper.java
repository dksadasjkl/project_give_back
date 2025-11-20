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

    // 관리자

    // 관리자 전체 주문 목록 (페이징)
    List<StoreOrder> selectOrdersByUserPagedForAdmin(
            @Param("offset") int offset,
            @Param("size") int size
    );

    // 관리자 전체 주문 개수
    int countAllOrders();

    // 관리자 단일 주문 조회
    StoreOrder selectOrderDetailAdmin(@Param("orderId") int orderId);

    // 관리자 주문 상태 변경
    int updateOrderStatusAdmin(@Param("orderId") int orderId, @Param("status") String status);

    // 관리자 주문 삭제
    int deleteStoreOrderAdmin(@Param("orderId") int orderId);
}