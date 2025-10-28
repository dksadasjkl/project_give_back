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
}
