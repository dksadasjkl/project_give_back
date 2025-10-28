package com.project.give.service;

import com.project.give.dto.store.request.PostStoreProductRequestDto;
import com.project.give.dto.store.request.PutStoreProductRequestDto;
import com.project.give.dto.store.response.GetStoreProductResponseDto;
import com.project.give.dto.store.response.GetStoreProductSearchRequestDto;
import com.project.give.entity.StoreProduct;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreProductService {

    @Autowired
    private StoreProductMapper storeProductMapper;

    //  상품 등록
    public void createProduct(PostStoreProductRequestDto dto) {
        StoreProduct product = dto.toEntity();
        int result = storeProductMapper.insertStoreProduct(product);
        if (result != 1) throw new DataSaveException("상품 등록 실패");
    }

    //  단일 상품 조회
    public GetStoreProductResponseDto getProduct(int productId) {
        return storeProductMapper.selectStoreProductById(productId).toResponseDto();
    }

    //  전체 상품 조회
    public List<GetStoreProductResponseDto> getProducts() {
        return storeProductMapper.selectAllStoreProducts()
                .stream().map(StoreProduct::toResponseDto).collect(Collectors.toList());
    }

    //  페이지네이션 조회
    public List<GetStoreProductResponseDto> loadMoreProducts(GetStoreProductSearchRequestDto dto) {
        List<StoreProduct> products = storeProductMapper.selectStoreProductsWithPaging(
                dto.getStartIndex(), dto.getCount(), dto.getCategoryId(), dto.getSearchTypeId());
        return products.stream().map(StoreProduct::toResponseDto).collect(Collectors.toList());
    }

    // 총 상품 개수 조회
    public int getProductCount(GetStoreProductSearchRequestDto dto) {
        return storeProductMapper.selectStoreProductCount(dto.getCategoryId());
    }

    //  상품 수정
    public void updateProduct(int productId, PutStoreProductRequestDto dto) {
        storeProductMapper.updateStoreProduct(dto.toEntity(productId));
    }

    //  상품 삭제
    public void deleteProduct(int productId) {
        storeProductMapper.deleteStoreProduct(productId);
    }
}