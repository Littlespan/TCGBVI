package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface DubboUserService {
    void saveUser(User user);

    String doLogin(User user);

    void delete(String ticket);
}
