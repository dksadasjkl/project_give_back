package com.project.give.service.admin.user;

import com.project.give.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getUserList(int page, int size) {
        int offset = (page - 1) * size;

        List<Map<String, Object>> list = userMapper.selectUserListAdmin(offset, size);
        int total = userMapper.countUsersAdmin();

        Map<String, Object> result = new HashMap<>();
        result.put("items", list);
        result.put("total", total);

        return result;
    }

    @Override
    public Object getUserDetail(int userId) {
        return userMapper.selectUserDetailAdmin(userId);
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }
}
