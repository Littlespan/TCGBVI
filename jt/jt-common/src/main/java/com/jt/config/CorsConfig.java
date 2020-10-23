package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 15:39
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    //在后端 配置cors允许访问的策略
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST")   //自定义允许跨域的请求类型
                .allowedOrigins("*")            //任意网址都可以访问
                .allowCredentials(true)         //是否允许携带cookie
                .maxAge(1800);
    }
}
