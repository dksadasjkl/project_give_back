package com.project.give.repository;

import com.project.give.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public int saveUser(User user); // 회원 정보 저장
    public User findUserByUsername(String username); // username으로 회원 조회
    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);
}
