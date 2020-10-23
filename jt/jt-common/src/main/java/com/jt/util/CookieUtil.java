package com.jt.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/21 10:14
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public class CookieUtil {

    public static void addCookie(HttpServletResponse response , String cookieName,String cookieValue,Integer maxAge,String domain){
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response , String cookieName,String domain){
        addCookie(response, cookieName, "",0,domain);
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies.length > 0){
            for(Cookie cookie : cookies){
                if(cookieName.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
