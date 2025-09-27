package com.project.give.repository;

import com.project.give.entity.OAuth2;
import com.project.give.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 회원 저장
    public int saveUser(User user);

    // 역할 저장
    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);

    // OAuth2 정보 저장
    public int saveOAuth2(OAuth2 oAuth2);

    // 회원 조회
    public User findUserByUsername(String username);
    public User findNicknameByUserId(@Param("userId") int userId);
    public User findUserByOAuth2name(String oAuth2name);
    public User findUserByEmail(String email);
    public User findByNameAndEmail(String name, String email);

    // 비밀번호 변경 (로그인 상태)
    public int updatePasswordByUserId(@Param("userId") int userId, @Param("password") String password);

    // 비밀번호 초기화 (이메일 기준)
    public int resetPasswordByEmail(@Param("userId") int id, @Param("password") String password);

    // 일반 회원 삭제 (소프트 삭제)
    public void deleteUser(@Param("userId") int userId);

    // OAuth 회원 삭제
    public void deleteOAuthUser(@Param("userId") int userId);

    public int countOAuthUser(@Param("userId") int userId);

    // 중복 체크
    public int countByUsername(@Param("username") String username);
    public int countByNickname(@Param("nickname") String nickname);
}
