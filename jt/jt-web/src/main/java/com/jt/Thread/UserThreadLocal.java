package com.jt.Thread;

import com.jt.pojo.User;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/22 11:23
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public class UserThreadLocal {

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void set(User user){
        threadLocal.set(user);
    }

    public static User get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
