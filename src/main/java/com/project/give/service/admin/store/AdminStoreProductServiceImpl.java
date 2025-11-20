package com.project.give.service.admin.store.impl;

import com.project.give.dto.store.request.PostStoreProductRequestDto;
import com.project.give.dto.store.request.PutStoreProductRequestDto;
import com.project.give.entity.StoreProduct;
import com.project.give.repository.StoreProductMapper;
import com.project.give.service.admin.store.AdminStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminStoreProductServiceImpl implements AdminStoreProductService {

    @Autowired
    private StoreProductMapper storeProductMapper;

    @Override
    public void createProduct(PostStoreProductRequestDto dto) {
        storeProductMapper.insertStoreProduct(dto.toEntity());
    }

    @Override
    public Map<String, Object> getProductList(int page, int size, Integer categoryId, Integer searchTypeId) {

        int offset = (page - 1) * size;

        // ⭐ 관리자용 전체 조회 사용
        List<StoreProduct> list = storeProductMapper.selectAdminStoreProductsWithPaging(
                offset,
                size,
                categoryId
        );

        int total = storeProductMapper.selectAdminStoreProductCount(categoryId);

        Map<String, Object> result = new HashMap<>();
        result.put("items", list);
        result.put("total", total);

        return result;
    }

    @Override
    public StoreProduct getProductDetail(int productId) {
        return storeProductMapper.selectStoreProductById(productId);
    }

    @Override
    public void updateProduct(int productId, PutStoreProductRequestDto dto) {
        storeProductMapper.updateStoreProduct(dto.toEntity(productId));
    }

    @Override
    public void deleteProduct(int productId) {
        storeProductMapper.deleteStoreProduct(productId);
    }
}
