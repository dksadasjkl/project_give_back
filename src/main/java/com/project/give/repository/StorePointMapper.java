package com.project.give.repository;

import com.project.give.entity.StorePoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorePointMapper {
    public int insertPoint(StorePoint storePoint);
    // Mapper.java
    List<StorePoint> selectPointsByUserPaged(@Param("userId") int userId,
                                             @Param("offset") int offset,
                                             @Param("size") int size);
    int countPointsByUser(@Param("userId") int userId);
}
