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

    // 메인 배너용 인기 상품 1개 가져오기
    StoreProduct selectTopStoreProduct();
}