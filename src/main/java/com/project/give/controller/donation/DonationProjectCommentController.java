package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectCommentRequestDto;
import com.project.give.service.DonationProjectCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donation-project-comments")
public class DonationProjectCommentController {

    @Autowired
    private DonationProjectCommentService donationProjectCommentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<?> createDonationProjectComment(@RequestBody PostDonationProjectCommentRequestDto postDonationProjectCommentRequestDto) {
        donationProjectCommentService.createDonationProjectComment(postDonationProjectCommentRequestDto);
        return ResponseEntity.created(null).body("댓글 작성 완료");
    }
}
