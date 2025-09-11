package com.project.give.repository;

import com.project.give.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int saveUser(User user); // 회원 정보 저장
    public User findUserByUsername(String username); // username으로 회원 조회
}
