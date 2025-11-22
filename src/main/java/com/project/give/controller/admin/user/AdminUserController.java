package com.project.give.controller.admin.user;

import com.project.give.service.admin.user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    // 유저 목록 조회
    @GetMapping
    public ResponseEntity<?> getUserList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(adminUserService.getUserList(page, size));
    }

    // 유저 상세 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetail(@PathVariable int userId) {
        return ResponseEntity.ok(adminUserService.getUserDetail(userId));
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        adminUserService.deleteUser(userId);
        return ResponseEntity.ok("유저 삭제 완료");
    }
}
