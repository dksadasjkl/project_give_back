package com.project.give.controller.store;

import com.project.give.dto.store.request.PostStoreCartRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/cart")
public class StoreCartController {

    @Autowired
    private StoreCartService storeCartService;

    //  장바구니 추가
    @PostMapping
    public ResponseEntity<?> addToCart(@AuthenticationPrincipal PrincipalUser principalUser,
                                       @RequestBody PostStoreCartRequestDto dto) {
        storeCartService.addToCart(principalUser.getUserId(), dto);
        return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
    }

    //  내 장바구니 조회
    @GetMapping
    public ResponseEntity<?> getMyCart(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(storeCartService.getMyCart(principalUser.getUserId()));
    }

    //  수량 수정
    @PutMapping("/{cartId}")
    public ResponseEntity<?> updateCartQuantity(@PathVariable int cartId, @RequestParam int quantity) {
        storeCartService.updateQuantity(cartId, quantity);
        return ResponseEntity.ok("장바구니 수량이 변경되었습니다.");
    }

    //  상품 삭제
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable int cartId) {
        storeCartService.deleteCartItem(cartId);
        return ResponseEntity.ok("상품이 장바구니에서 삭제되었습니다.");
    }

    // 전체 삭제
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal PrincipalUser principalUser) {
        storeCartService.clearCart(principalUser.getUserId());
        return ResponseEntity.ok("장바구니를 비웠습니다.");
    }
}
