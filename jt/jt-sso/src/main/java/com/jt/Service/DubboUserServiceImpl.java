package com.jt.Service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.Mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.util.CookieUtil;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/20 14:09
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Service
public class DubboUserServiceImpl implements DubboUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void saveUser(User user) {
        String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setEmail(user.getPhone())
            .setPassword(md5);
        userMapper.insert(user);
    }

    @Override
    public String doLogin(User user) {
        String md5pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5pass);
        QueryWrapper<User> qw = new QueryWrapper<>(user);
        User userDB = userMapper.selectOne(qw);
        if(userDB == null){
            return null;
        }else {
            String ticket = UUID.randomUUID().toString();
            userDB.setPassword("123456你信不？");
            String userJson = ObjectMapperUtil.toJson(userDB);
            jedisCluster.setex(ticket,7*24*60*60,userJson);
            return ticket;
        }
    }

    @Override
    public void delete(String ticket) {
        jedisCluster.del(ticket);
    }

}
