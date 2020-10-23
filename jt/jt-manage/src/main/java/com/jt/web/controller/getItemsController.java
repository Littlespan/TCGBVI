package com.jt.web.controller;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/19 11:31
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@RestController
public class getItemsController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/getItems")
    public List<Item> getItems(){
        return itemService.getItems();
    }
}
