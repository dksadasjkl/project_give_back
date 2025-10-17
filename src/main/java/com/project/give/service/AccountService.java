package com.project.give.service;

import com.project.give.dto.account.request.FindUsernameRequestDto;
import com.project.give.dto.account.request.PasswordResetRequestDto;
import com.project.give.dto.account.request.UserPasswordRequestDto;
import com.project.give.entity.PrincipalUser;
import com.project.give.entity.User;
import com.project.give.exception.MyAccountException;
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

        User user = userMapper.findByUsernameAndEmail(passwordResetRequestDto.getUsername(), passwordResetRequestDto.getEmail());
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
        if (username == null || username.length() <= 4) return username; // 너무 짧으면 마스킹 생략

        int len = username.length();
        int visibleStart = 2; // 앞에서 몇 글자 보일지
        int visibleEnd = 2;   // 뒤에서 몇 글자 보일지

        StringBuilder masked = new StringBuilder();
        masked.append(username, 0, visibleStart); // 앞 2글자 그대로

        for (int i = visibleStart; i < len - visibleEnd; i++) {
            masked.append("*"); // 중앙 마스킹
        }

        masked.append(username.substring(len - visibleEnd)); // 뒤 2글자 그대로

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
    
    // 프로필 닉네임 수정
    @Transactional(rollbackFor = Exception.class)
    public void updateProfile(int userId, String nickname, String profileImageUrl) {

        // 닉네임이 있으면 중복 체크
        if (nickname != null && !nickname.isEmpty()) {
            if (checkNicknameExists(nickname)) {
                throw new MyAccountException("이미 사용 중인 닉네임입니다.");
            }
        }

        // 값이 있으면 UserMapper로 업데이트
        userMapper.updateProfile(userId, nickname, profileImageUrl);
    }
}