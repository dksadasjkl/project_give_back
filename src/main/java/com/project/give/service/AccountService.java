package com.project.give.service;

import com.project.give.dto.account.request.FindUsernameRequestDto;
import com.project.give.dto.account.request.PasswordResetRequestDto;
import com.project.give.dto.account.request.UserPasswordRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.entity.User;
import com.project.give.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;

@Service
public class AccountService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.mail.address}")
    private String fromMailAddress;
    @Value("${server.deploy-address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;

    // 비밀번호 초기화 + 메일 발송
    @Transactional(rollbackFor = Exception.class)
    public boolean resetPassword(PasswordResetRequestDto passwordResetRequestDto) {
        User user = userMapper.findUserByEmail(passwordResetRequestDto.getEmail());
        if (user == null) return false;

        // 임시 비밀번호 생성
        String tempPassword = generateRandomPassword(10);

        // DB에 암호화된 비밀번호 저장
        String encodedPassword = bCryptPasswordEncoder.encode(tempPassword);
        userMapper.resetPasswordByEmail(user.getUserId(), encodedPassword);

        // 메일 전송
        return sendTempPasswordMail(passwordResetRequestDto.getEmail(), tempPassword);
    }

    // 메일 발송
    private boolean sendTempPasswordMail(String toEmail, String tempPassword) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

            helper.setSubject("비밀번호 초기화 안내");
            helper.setFrom(fromMailAddress);
            helper.setTo(toEmail);

            String content = "<div>"
                    + "<h1>임시 비밀번호 안내</h1>"
                    + "<p>회원님의 임시 비밀번호는 아래와 같습니다.</p>"
                    + "<p><strong>" + tempPassword + "</strong></p>"
                    + "<p>로그인 후 반드시 비밀번호를 변경해 주세요.</p>"
                    + "</div>";

            message.setText(content, "utf-8", "html");
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 임시 비밀번호 생성
    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
    
    // 유저 아이디 찾기
    public String findUsernameByNameAndEmail(FindUsernameRequestDto findUsernameRequestDto) {
        User user = userMapper.findByNameAndEmail(findUsernameRequestDto.getName(), findUsernameRequestDto.getEmail());
        return user != null ? maskUsername(user.getUsername()) : null;
    }

    // 유저 아이디 부분 마스킹
    private String maskUsername(String username) {
        if (username == null || username.length() < 3) return username; // 마스킹 생략

        int len = username.length();
        int maskStart = 1;
        int maskEnd = len - 1;

        StringBuilder masked = new StringBuilder();
        masked.append(username.charAt(0)); // 첫 글자
        for (int i = maskStart; i < maskEnd; i++) {
            masked.append("*"); // 중앙 마스킹
        }
        masked.append(username.charAt(len - 1)); // 마지막 글자

        return masked.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(UserPasswordRequestDto userPasswordRequestDto, PrincipalUser principalUser) {
        // 1. 현재 로그인 되어있는 비밀번호와 요청때 받은 현재 비밀번호가 일치하는지 확인.
        if (!bCryptPasswordEncoder.matches(userPasswordRequestDto.getOldPassword(), principalUser.getPassword())) {
            throw new BadCredentialsException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 2. 새 비밀번호와 새 비밀번호 확인이 일치하는지.
        if (!userPasswordRequestDto.getNewPassword().equals(userPasswordRequestDto.getNewPasswordCheck())) {
            throw new BadCredentialsException("새 비밀번호가 일치하지 않습니다.");
        }

        // 3. 새 비밀번호 저장
        String encodedNewPassword = bCryptPasswordEncoder.encode(userPasswordRequestDto.getNewPassword());
        userMapper.updatePasswordByUserId(principalUser.getUserId(), encodedNewPassword);
    }


    // 일반 회원 탈퇴
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserById(int userId) {
        if (userMapper.countOAuthUser(userId) > 0) {
            userMapper.deleteOAuthUser(userId);
        }
        userMapper.deleteUser(userId);
    }

    // 유저네임 중복체크
    public boolean checkUsernameExists(String username) {
        return userMapper.countByUsername(username) > 0;
    }

    // 닉네임 중복체크
    public boolean checkNicknameExists(String nickname) {
        return userMapper.countByNickname(nickname) > 0;
    }

}