package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.util.CookieUtil;
import com.jt.vo.SysResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Response;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 11:29
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Reference(timeout = 3000,check = false)
    private DubboUserService userService;

    @RequestMapping("{modelName}")
    public String userLogin(@PathVariable String modelName) {
        return modelName;
    }


    @RequestMapping("doRegister")
    @ResponseBody
    public SysResult saveUser(User user) {
        userService.saveUser(user);
        return SysResult.success();
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public SysResult checkUser(User user, HttpServletResponse response) {
        String ticket = userService.doLogin(user);
        if (StringUtils.isEmpty(ticket)) {
            return SysResult.fail();
        } else {
            CookieUtil.addCookie(response, "JT_TICKET", ticket, 7 * 24 * 60 * 60, "jt.com");
            return SysResult.success();
        }
    }

    @RequestMapping("logout")
    public String doLogout(HttpServletRequest request,HttpServletResponse response){
        String ticket = CookieUtil.getCookieValue(request,"JT_TICKET");
        if(!StringUtils.isEmpty(ticket)){
            userService.delete(ticket);
            CookieUtil.deleteCookie(response,"JT_TICKET","jt.com");
        }
        return "redirect:/";
    }
}
