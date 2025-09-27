package com.project.give.controller.account;

import com.project.give.dto.account.request.FindUsernameRequestDto;
import com.project.give.dto.account.request.PasswordResetRequestDto;
import com.project.give.dto.account.request.UserPasswordRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.exception.MyAccountException;
import com.project.give.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipalUser() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @PutMapping("/passwordReset")
    public ResponseEntity<?> updateUser(@RequestBody PasswordResetRequestDto passwordResetRequestDto){
        boolean result = accountService.resetPassword(passwordResetRequestDto);
        if(result) {
            return ResponseEntity.ok("비밀번호 초기화 완료");
        } else {
            return ResponseEntity.badRequest().body("해당 이메일을 찾을 수 없습니다.");
        }
    }

//   GET 방식 (쿼리스트링 노출) -> POST 방식 (바디에 숨김)
    @PostMapping("/find-username")
    public ResponseEntity<?> findUsername(@RequestBody FindUsernameRequestDto findUsernameRequestDto) {
        String username = accountService.findUsernameByNameAndEmail(findUsernameRequestDto);

        return username != null
                ? ResponseEntity.ok(username)
                : ResponseEntity.badRequest().body("가입된 사용자가 없습니다.");
    }

    @PutMapping("/password")
    public ResponseEntity<?> modifyPassword(
            @RequestBody UserPasswordRequestDto userPasswordRequestDto,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        accountService.changePassword(userPasswordRequestDto, principalUser);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }
    // 회원 탈퇴 (로그인 상태, 일반 회원 + 소셜 회원)
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserById(@AuthenticationPrincipal PrincipalUser principalUser) {
        System.out.println(principalUser);
        accountService.deleteUserById(principalUser.getUserId());

        return ResponseEntity.ok("회원 탈퇴 완료");
    }

}
