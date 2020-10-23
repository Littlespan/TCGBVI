package com.jt.interceptor;

import com.jt.Thread.UserThreadLocal;
import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/22 9:26
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtil.getCookieValue(request, "JT_TICKET");
        if(!StringUtils.isEmpty(ticket)){
            if(jedisCluster.exists(ticket)){
                User user = ObjectMapperUtil.toObj(jedisCluster.get(ticket), User.class);
//                request.setAttribute("user",user);
                UserThreadLocal.set(user);
                return true;
            }
        }
            response.sendRedirect("/user/login");
            return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
