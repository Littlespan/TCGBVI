package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.service.DubboItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/21 11:22
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Controller
@RequestMapping("/items/")
public class ItemController {
    @Reference(timeout = 3000,check = false)
    private DubboItemService itemService;


    @RequestMapping("{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        model.addAttribute("item",itemService.findItemById(itemId));
        model.addAttribute("itemDesc", itemService.findItemDescById(itemId));
        return "item";
    }

}
