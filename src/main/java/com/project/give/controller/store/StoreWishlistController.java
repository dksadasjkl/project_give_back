package com.project.give.controller.store;

import com.project.give.entity.PrincipalUser;
import com.project.give.service.StoreWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/wishlist")
public class StoreWishlistController {

    @Autowired
    private StoreWishlistService storeWishlistService;

    // 찜 추가
    @PostMapping("/{productId}")
    public ResponseEntity<?> addWishlist(@PathVariable int productId,
                                         @AuthenticationPrincipal PrincipalUser principalUser) {
        storeWishlistService.addWishlist(principalUser.getUserId(), productId);
        return ResponseEntity.ok("상품이 찜 목록에 추가되었습니다.");
    }

    // 찜 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeWishlist(@PathVariable int productId,
                                            @AuthenticationPrincipal PrincipalUser principalUser) {
        int result = storeWishlistService.removeWishlist(principalUser.getUserId(), productId);

        if (result == 0) {
            return ResponseEntity.badRequest().body("해당 상품이 찜 목록에 없습니다."); // ❌ 컴파일 오류
        }

        return ResponseEntity.ok("상품이 찜 목록에서 제거되었습니다.");
    }



    // 내 찜 목록 조회 (페이지네이션 추가)
    @GetMapping
    public ResponseEntity<?> getMyWishlist(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        return ResponseEntity.ok(storeWishlistService.getMyWishlistPaged(principalUser.getUserId(), page, size));
    }
}
