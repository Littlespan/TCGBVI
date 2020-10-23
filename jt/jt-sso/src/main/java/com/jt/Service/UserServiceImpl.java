package com.jt.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.Mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 10:35
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Service
public class UserServiceImpl implements UserService {
    static Map<Integer,String> columnMap = new HashMap<>();
    static{
        columnMap.put(1, "username");
        columnMap.put(2, "phone");
        columnMap.put(3, "email");
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public List<User> findUserAll() {
        return userMapper.selectList(null);
    }

    @Override
    public Boolean check(String param, Integer type) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        String column = columnMap.get(type);
//        if(type == 1){
//            column = "username";
//        }else if(type == 2){
//            column = "phone";
//        }else if(type == 3){
//            column = "email";
//        }
        qw.eq(column,param);
        if(userMapper.selectCount(qw)>0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void doRegister(User user) {
        userMapper.insert(user);
    }

    @Override
    public String doQuery(String ticket) {
        return jedisCluster.get(ticket);

    }

}
