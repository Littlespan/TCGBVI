package com.jt.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.Service.UserService;
import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 10:31
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUserAll")
    public List<User> findUserAll(){
        return userService.findUserAll();
    }

    @RequestMapping("check/{param}/{type}")
    public JSONPObject check(@PathVariable String param , @PathVariable Integer type, String callback){
        Boolean flag = userService.check(param , type);
            return new JSONPObject(callback,SysResult.success(flag));
    }

    @RequestMapping("register")
    public String doRegister(User user){
        userService.doRegister(user);
        return ObjectMapperUtil.toJson(SysResult.success(user.getUsername()));
    }

    @RequestMapping("query/{ticket}")
    @ResponseBody
    public JSONPObject doQuery(@PathVariable String ticket, HttpServletResponse response, String callback){
        String user = userService.doQuery(ticket);
        if(StringUtils.isEmpty(user)){
            CookieUtil.deleteCookie(response,"JT_TICKET","jt.com");
            return new JSONPObject(callback,SysResult.fail());
        }else{
            return new JSONPObject(callback,SysResult.success(user));
        }
    }

}
