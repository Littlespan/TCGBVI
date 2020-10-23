package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/21 11:33
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public interface DubboItemService {
    Item findItemById(Long id);
    ItemDesc findItemDescById(Long id);
}
