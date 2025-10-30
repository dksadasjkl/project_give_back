package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreProductRequestDto;
import com.project.give.dto.store.request.PutStoreProductRequestDto;
import com.project.give.dto.store.response.GetStoreProductSearchRequestDto;
import com.project.give.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/products")
public class StoreProductController {

    @Autowired
    private StoreProductService storeProductService;

    //  상품 등록
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody PostStoreProductRequestDto dto) {
        storeProductService.createProduct(dto);
        return ResponseEntity.created(null).body(true);
    }

    //  특정 상품 상세 조회
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable int productId) {
        return ResponseEntity.ok(storeProductService.getProduct(productId));
    }

    //  전체 상품 목록 조회 (기본형)
    @GetMapping
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(storeProductService.getProducts());
    }

    //  페이지네이션
    @GetMapping("/load-more")
    public ResponseEntity<?> loadMoreProducts(GetStoreProductSearchRequestDto dto) {
        return ResponseEntity.ok(storeProductService.loadMoreProducts(dto));
    }

    //  총 상품 개수 조회
    @GetMapping("/count")
    public ResponseEntity<?> getProductCount(GetStoreProductSearchRequestDto dto) {
        return ResponseEntity.ok(storeProductService.getProductCount(dto));
    }

    //  상품 수정
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable int productId,
                                           @RequestBody PutStoreProductRequestDto dto) {
        storeProductService.updateProduct(productId, dto);
        return ResponseEntity.ok("상품 수정 완료");
    }

    //  상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        storeProductService.deleteProduct(productId);
        return ResponseEntity.ok("상품 삭제 완료");
    }
}