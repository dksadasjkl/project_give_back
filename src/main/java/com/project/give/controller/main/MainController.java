package com.project.give.controller.main;

import com.project.give.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private MainService mainService;

    // 🔹 배너용 1개씩 가져오기
    @GetMapping("/top")
    public ResponseEntity<?> getMainTopItems() {
        return ResponseEntity.ok(mainService.getMainTopItems());
    }

    // 🔹 메인 섹션용 TOP3
    @GetMapping("/recommend")
    public ResponseEntity<?> getMainRecommendations() {
        return ResponseEntity.ok(mainService.getMainRecommend());
    }

}
