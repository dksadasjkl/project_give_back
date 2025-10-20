package com.project.give.controller.donation;

import com.project.give.entity.PrincipalUser;
import com.project.give.service.DonationProjectCommentService;
import com.project.give.service.DonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
public class MyDonationsController {

    @Autowired
    private DonationProjectService donationProjectService;

    @Autowired
    private DonationProjectCommentService donationProjectCommentService;


    // 1. 내가 참여한 기부 리스트 조회
    @GetMapping("/donations")
    public ResponseEntity<?> getMyDonations(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(donationProjectService.getMyDonations(principalUser));
    }

    @GetMapping("/donation-comments")
    public ResponseEntity<?> getMyDonationComments(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(donationProjectCommentService.getMyDonationComments(principalUser));
    }
}
