package com.project.give.repository;

import com.project.give.entity.StoreProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreProductMapper {

    public int insertStoreProduct(StoreProduct storeProduct);

    public StoreProduct selectStoreProductById(@Param("productId") int productId);

    public List<StoreProduct> selectAllStoreProducts();

    public List<StoreProduct> selectStoreProductsWithPaging(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("categoryId") int categoryId,
            @Param("searchTypeId") int searchTypeId
    );

    public int selectStoreProductCount(@Param("categoryId") int categoryId);

    public int updateStoreProduct(StoreProduct storeProduct);

    public int deleteStoreProduct(@Param("productId") int productId);

    // 메인 배너용(단일)
    StoreProduct selectTopStoreProduct();

    // 메인 추천 섹션용 TOP N
    List<StoreProduct> selectTopStoreProducts(@Param("limit") int limit);

    // 관리자
    List<StoreProduct> selectAdminStoreProductsWithPaging(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("categoryId") Integer categoryId
    );

    int selectAdminStoreProductCount(
            @Param("categoryId") Integer categoryId
    );
}