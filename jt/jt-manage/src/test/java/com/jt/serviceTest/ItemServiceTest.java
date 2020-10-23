package com.jt.serviceTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.service.ItemService;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/25 18:37
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void ItemTest(){
        QueryWrapper<Item> qw = new QueryWrapper<>();
        qw.orderByDesc("updated");
        IPage<Item> iPage = new Page<>(1,20);
        iPage = itemMapper.selectPage(iPage,qw);
        System.out.println(iPage.getTotal());
    }
}
