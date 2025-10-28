package com.project.give.repository;

import com.project.give.entity.StorePoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorePointMapper {
    public int insertPoint(StorePoint storePoint);
    public List<StorePoint> selectPointsByUser(@Param("userId") int userId);
}
