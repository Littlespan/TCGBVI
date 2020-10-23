package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/21 11:34
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Service
public class DubboItemServiceImpl implements DubboItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public Item findItemById(Long id) {
        return itemMapper.selectById(id);
    }

    @Override
    public ItemDesc findItemDescById(Long id) {
        return itemDescMapper.selectById(id);
    }
}
