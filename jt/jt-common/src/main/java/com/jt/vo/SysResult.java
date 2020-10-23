package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/27 14:51
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult implements Serializable {
    private static final long serialVersionUID = -8914120021701384873L;
    private Integer status;
    private String msg;
    private Object data;


    //封装一些静态API 简化用户调用过程.
    public static SysResult fail(){

        return new SysResult(400,"服务器调用失败！！！",null);
    }

    public static SysResult fail(Object data){

        return new SysResult(400,"业务执行失败！！！",data);
    }

    public static SysResult success(){

        return new SysResult(200, "业务执行成功！！！", null);
    }

    public static SysResult success(Object data){

        return new SysResult(200, "业务执行成功！！！", data);
    }

    public static SysResult success(String msg , Object data){

        return new SysResult(200, msg , data );
    }

}
