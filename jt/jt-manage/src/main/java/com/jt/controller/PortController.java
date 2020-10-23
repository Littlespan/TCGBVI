package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/30 15:24
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@RestController
public class PortController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/getPort")
    public String getPort(){
        return "当前服务器访问的端口号：" + port;
    }
}
