package com.project.give.service.admin.user;

import java.util.Map;

public interface AdminUserService {
    Map<String, Object> getUserList(int page, int size);
    Object getUserDetail(int userId);
    void deleteUser(int userId);
}
