package com.project.give.repository;

import com.project.give.entity.StoreWishlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreWishlistMapper {
    public int insertWishlist(@Param("userId") int userId, @Param("productId") int productId);
    public int deleteWishlist(@Param("userId") int userId, @Param("productId") int productId);
    public boolean existsWishlist(@Param("userId") int userId, @Param("productId") int productId);
    public List<StoreWishlist> selectWishlistByUser(@Param("userId") int userId);
}
