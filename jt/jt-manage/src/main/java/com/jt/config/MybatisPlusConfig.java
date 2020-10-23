package com.jt.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/26 9:47
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */

@Configuration
public class MybatisPlusConfig {

    //需要将对象交给容器管理Map集合  map<paginationInterceptor方法名,实例化之后的对象>
    //Spring注入 1.按照类型注入   2.可以按照名字注入
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationIntkerceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
