package com.project.give.controller.donation;

import com.project.give.dto.donation.request.PostDonationProjectDetailRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectDetailRequestDto;
import com.project.give.entity.DonationProjectDetail;
import com.project.give.service.DonationProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation-project-details")
public class DonationProjectDetailController {

    @Autowired
    private DonationProjectDetailService donationProjectDetailService;

    // 단일 저장
    @PostMapping
    public ResponseEntity<?> createDetail(@RequestBody PostDonationProjectDetailRequestDto postDonationProjectDetailRequestDto) {
        donationProjectDetailService.createDonationProjectDetail(postDonationProjectDetailRequestDto);
        return ResponseEntity.created(null).body(true);
    }

    // 배치 저장
    @PostMapping("/batch")
    public ResponseEntity<?> createDetailsBatch(@RequestBody List<PostDonationProjectDetailRequestDto> projectDetailRequestDtoList) {
        donationProjectDetailService.createDonationProjectDetailBatch(projectDetailRequestDtoList);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/{donationProjectId}")
    public ResponseEntity<?> getDetailsByDonationProjectId (@PathVariable int donationProjectId) {
        return ResponseEntity.ok(donationProjectDetailService.getDetailsByDonationProjectId(donationProjectId));
    }

    @DeleteMapping("/{donationProjectDetailId}")
    public ResponseEntity<?> deleteDonationProjectDetailById(@PathVariable int donationProjectDetailId) {
        donationProjectDetailService.deleteDonationProjectDetailById(donationProjectDetailId);
        return ResponseEntity.ok("기부 상세페이지 삭제 완료");
    }

    @PutMapping("/{donationProjectDetailId}")
    public ResponseEntity<?> updateDonationProjectDetail (
            @PathVariable int donationProjectDetailId,
            @RequestBody PutDonationProjectDetailRequestDto putDonationProjectDetailRequestDto) {
        donationProjectDetailService.updateDonationProjectDetail(donationProjectDetailId, putDonationProjectDetailRequestDto);
        return ResponseEntity.ok("기부 프로젝트 상세 수정 완료");
    }
}
