package com.project.give.controller.account;

import com.project.give.aop.annotation.ValidAspect;
import com.project.give.dto.account.request.*;
import com.project.give.entity.PrincipalUser;
import com.project.give.exception.MyAccountException;
import com.project.give.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // principal 요청  (로그인 상태) - 전역
    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipalUser() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    // 비밀번호 초기화 (로그인 상태) - 로그인 / 회원가입페이지
    @PutMapping("/passwordReset")
    public ResponseEntity<?> updateUser(@RequestBody PasswordResetRequestDto passwordResetRequestDto){
        boolean result = accountService.resetPassword(passwordResetRequestDto);
        if(result) {
            return ResponseEntity.ok("비밀번호 초기화 완료");
        } else {
            return ResponseEntity.badRequest().body("사용자의 정보를 확인해주세요.");
        }
    }

    //   GET 방식 (쿼리스트링 노출) -> POST 방식 (바디에 숨김) 아이디 찾기 - 로그인 / 회원가입페이지
    @PostMapping("/find-username")
    public ResponseEntity<?> findUsername(@RequestBody FindUsernameRequestDto findUsernameRequestDto) {
        String username = accountService.findUsernameByNameAndEmail(findUsernameRequestDto);
        return username != null
                ? ResponseEntity.ok(username)
                : ResponseEntity.badRequest().body("사용자의 정보를 확인해주세요.");
    }

    // 비밀번호 변경 (로그인 상태) - 마이페이지
    @PutMapping("/password")
    public ResponseEntity<?> modifyPassword(
            @RequestBody UserPasswordRequestDto userPasswordRequestDto,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        accountService.changePassword(userPasswordRequestDto, principalUser);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }
    
    // 회원 탈퇴 (로그인 상태, 일반 회원 + 소셜 회원) - 마이페이지
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserById(@AuthenticationPrincipal PrincipalUser principalUser) {
        System.out.println(principalUser);
        accountService.deleteUserById(principalUser.getUserId());

        return ResponseEntity.ok("회원 탈퇴 완료");
    }

    // 유저네임 중복체크 - 로그인 / 회원가입페이지
    @ValidAspect
    @PostMapping("/username-check")
    public ResponseEntity<Boolean> checkUsername(
            @Valid @RequestBody UsernameCheckRequestDto usernameCheckRequestDto,
            BindingResult bindingResult) {
        boolean exists = accountService.checkUsernameExists(usernameCheckRequestDto.getUsername());
        return ResponseEntity.ok(exists);
    }

    // 닉네임 중복체크 - 로그인 / 회원가입페이지
    @ValidAspect
    @PostMapping("/nickname-check")
    public ResponseEntity<Boolean> checkNickname(
            @Valid @RequestBody NicknameCheckRequestDto nicknameCheckRequestDto,
            BindingResult bindingResult) {
        boolean exists = accountService.checkNicknameExists(nicknameCheckRequestDto.getNickname());
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestBody ProfileUpdateRequestDto profileUpdateRequestDto,
            @AuthenticationPrincipal PrincipalUser principalUser) {

        String nickname = profileUpdateRequestDto.getNickname();
        String profileImageUrl = profileUpdateRequestDto.getProfileImageUrl();

        if ((nickname == null || nickname.isBlank()) && (profileImageUrl == null || profileImageUrl.isBlank())) {
            return ResponseEntity.badRequest().body("닉네임 또는 프로필 이미지 중 하나는 입력해주세요.");
        }

        accountService.updateProfile(principalUser.getUserId(), nickname, profileImageUrl);

        return ResponseEntity.ok("프로필 업데이트 완료");
    }

}
