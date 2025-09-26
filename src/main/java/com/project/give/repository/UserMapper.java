package com.project.give.repository;

import com.project.give.entity.OAuth2;
import com.project.give.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public int saveUser(User user); // 회원 정보 저장
    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);
    public int saveOAuth2(OAuth2 oAuth2);
    public User findUserByUsername(String username); // username으로 회원 조회
    public User findNicknameByUserId(@Param("userId") int userId);
    public User findUserByOAuth2name(String oAuth2name);
    public User findUserByEmail(String email);
    int updatePassword(@Param("userId") int id, @Param("password") String password);

}
