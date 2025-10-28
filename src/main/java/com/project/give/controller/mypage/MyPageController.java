package com.project.give.controller.mypage;

import com.project.give.entity.PrincipalUser;
import com.project.give.service.DonationProjectCommentService;
import com.project.give.service.DonationProjectService;
import com.project.give.service.StoreOrderService;
import com.project.give.service.StoreShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private DonationProjectService donationProjectService;

    @Autowired
    private DonationProjectCommentService donationProjectCommentService;

    @Autowired
    private StoreOrderService storeOrderService;

    @Autowired
    private StoreShippingService storeShippingService;


    // 1. 내가 참여한 기부 리스트 조회
    @GetMapping("/donations")
    public ResponseEntity<?> getMyDonations(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(donationProjectService.getMyDonations(principalUser));
    }

    @GetMapping("/donation-comments")
    public ResponseEntity<?> getMyDonationComments(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(donationProjectCommentService.getMyDonationComments(principalUser));
    }

    @GetMapping("/fundings")
    public ResponseEntity<?> getMyFundings(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(donationProjectService.getMyFundings(principalUser));
    }

    // ✅ 내가 주문한 공감가게 상품 목록
    @GetMapping("/store-orders")
    public ResponseEntity<?> getMyStoreOrders(@AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUserId();
        return ResponseEntity.ok(storeOrderService.getOrdersByUser(userId));
    }

    @GetMapping("/store-shipping")
    public ResponseEntity<?> getMyShipping(@AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUserId();
        return ResponseEntity.ok(storeShippingService.getShippingByUser(userId));
    }
}
