package com.project.give.service.admin.store;

import com.project.give.dto.store.request.PostStoreProductRequestDto;
import com.project.give.dto.store.request.PutStoreProductRequestDto;

import java.util.Map;

public interface AdminStoreProductService {

    void createProduct(PostStoreProductRequestDto dto);
    Map<String, Object> getProductList(int page, int size, Integer categoryId, Integer searchTypeId);
    Object getProductDetail(int productId);
    void updateProduct(int productId, PutStoreProductRequestDto dto);
    void deleteProduct(int productId);
}
