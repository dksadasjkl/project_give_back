package com.project.give.repository;

import com.project.give.entity.StoreCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreCartMapper {

    public int insertCart(StoreCart storeCart);

    public List<StoreCart> selectCartByUser(@Param("userId") int userId);

    public int updateCartQuantity(@Param("cartId") int cartId, @Param("quantity") int quantity);

    public int deleteCart(@Param("cartId") int cartId);

    public boolean existsCartItem(@Param("userId") int userId, @Param("productId") int productId);

    public int clearCartByUser(@Param("userId") int userId);
}