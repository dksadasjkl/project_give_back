package com.project.give.controller.store;

import com.project.give.entity.PrincipalUser;
import com.project.give.service.StorePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/points")
public class StorePointController {

    @Autowired
    private StorePointService storePointService;

    // 내 포인트 내역 조회
    @GetMapping
    public ResponseEntity<?> getMyPoints(@AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(storePointService.getMyPoints(principalUser.getUserId()));
    }
}