package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectCommentRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectCommentRequestDto;
import com.project.give.service.DonationProjectCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 특정 프로젝트 댓글 조회
    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getComments(@PathVariable int donationProjectId) {
        return ResponseEntity.ok(donationProjectCommentService.getCommentsByProjectId(donationProjectId));
    }

    // 댓글 수정
    @PutMapping("/{donationProjectCommentId}")
    public ResponseEntity<?> updateDonationProjectComment(@PathVariable int donationProjectCommentId,
                                                          @RequestBody PutDonationProjectCommentRequestDto putDonationProjectCommentRequestDto) {
        donationProjectCommentService.updateDonationProjectComment(donationProjectCommentId, putDonationProjectCommentRequestDto);
        return ResponseEntity.ok("댓글 수정 완료");
    }
}
