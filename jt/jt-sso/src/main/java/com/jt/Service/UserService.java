package com.jt.Service;

import com.jt.pojo.User;

import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 10:33
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public interface UserService {
    List<User> findUserAll();

    Boolean check(String param , Integer type);

    void doRegister(User user);

    String doQuery(String ticket);
}
