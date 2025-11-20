package com.project.give.controller.admin.store;

import com.project.give.dto.store.request.PostStoreProductRequestDto;
import com.project.give.dto.store.request.PutStoreProductRequestDto;
import com.project.give.service.admin.store.AdminStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/store/products")
public class AdminStoreProductController {

    @Autowired
    private AdminStoreProductService adminStoreProductService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostStoreProductRequestDto dto) {
        adminStoreProductService.createProduct(dto);
        return ResponseEntity.ok("상품이 등록되었습니다.");
    }

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer searchTypeId
    ) {
        return ResponseEntity.ok(adminStoreProductService.getProductList(page, size, categoryId, searchTypeId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> detail(@PathVariable int productId) {
        return ResponseEntity.ok(adminStoreProductService.getProductDetail(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> update(@PathVariable int productId, @RequestBody PutStoreProductRequestDto dto) {
        adminStoreProductService.updateProduct(productId, dto);
        return ResponseEntity.ok("상품이 수정되었습니다.");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> delete(@PathVariable int productId) {
        adminStoreProductService.deleteProduct(productId);
        return ResponseEntity.ok("상품이 삭제되었습니다.");
    }
}
