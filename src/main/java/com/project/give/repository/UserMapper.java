package com.project.give.repository;

import com.project.give.entity.OAuth2;
import com.project.give.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 회원 저장
    int saveUser(User user);

    // 역할 저장
    int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);

    // OAuth2 정보 저장
    int saveOAuth2(OAuth2 oAuth2);

    // 회원 조회
    User findUserByUsername(String username);
    User findNicknameByUserId(@Param("userId") int userId);
    User findUserByOAuth2name(String oAuth2name);
    User findUserByEmail(String email);
    User findByNameAndEmail(String name, String email);

    // 비밀번호 변경 (로그인 상태)
    int updatePasswordByUserId(@Param("userId") int userId, @Param("password") String password);

    // 비밀번호 초기화 (이메일 기준)
    int resetPasswordByEmail(@Param("userId") int id, @Param("password") String password);

}
