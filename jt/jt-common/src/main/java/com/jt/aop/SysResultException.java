package com.jt.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.SysResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/28 15:34
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@RestControllerAdvice
public class SysResultException {

    @ExceptionHandler({RuntimeException.class, SQLException.class})
    public Object doHandleRuntimeException(Exception e, HttpServletRequest request) {
        String callback = request.getParameter("callback");
        e.printStackTrace();//在后台控制台打印异常.
        if(StringUtils.isEmpty(callback)){
            return SysResult.fail(e);
        }else{
            return new JSONPObject(callback, SysResult.fail(e));
        }


    }
}
