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
import org.springframework.web.bind.annotation.RequestParam;
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

    
    //  1. 내가 참여한 기부 목록 (페이지네이션)
    @GetMapping("/donations")
    public ResponseEntity<?> getMyDonations(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        return ResponseEntity.ok(donationProjectService.getMyDonationsPaged(principalUser, page, size));
    }

    //  2. 내가 참여한 펀딩 목록 (페이지네이션)
    @GetMapping("/fundings")
    public ResponseEntity<?> getMyFundings(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        return ResponseEntity.ok(donationProjectService.getMyFundingsPaged(principalUser, page, size));
    }

    //  2. 내가 참여한 댓글 목록 (페이지네이션)
    @GetMapping("/donation-comments")
    public ResponseEntity<?> getMyDonationComments(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(donationProjectCommentService.getMyDonationCommentsPaged(principalUser, page, size));
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
