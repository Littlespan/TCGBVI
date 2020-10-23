package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 10:02
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@SpringBootApplication
@MapperScan("com.jt.Mapper")
public class SpringBootRun {
    public static void main(String[] args) {

        SpringApplication.run(SpringBootRun.class,args);
    }
}
