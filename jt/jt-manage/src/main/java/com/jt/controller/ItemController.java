package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/item/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("query")
    public EasyUITable queryItemByPage(Integer page , Integer rows){
        EasyUITable easyUITable = itemService.query(page,rows);
        return easyUITable;
    }

    @RequestMapping("delete")
    public SysResult deleteById(Integer... ids){
        return itemService.deleteById(ids);
    }

    @RequestMapping("save")
    public SysResult saveItem(Item item, ItemDesc itemDesc){
        return itemService.saveItem(item,itemDesc);
    }

    @RequestMapping("instock")
    public SysResult instockItemById(Long... ids){
        return itemService.instockItemByIds(ids);
    }

    @RequestMapping("reshelf")
    public SysResult reshelfItemById(Long... ids){
        return itemService.reshelfItemById(ids);
    }

    @RequestMapping("update")
    public SysResult updateItemById(Item item,ItemDesc itemDesc){
        return itemService.updateItemById(item,itemDesc);
    }

    @RequestMapping("query/item/desc/{itemId}")
    public SysResult queryItemDescById(@PathVariable Long itemId){
        return itemService.findItemDescById(itemId);
    }

}
