package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

import java.util.List;

public interface ItemService {
    EasyUITable query(Integer page , Integer rows);
    SysResult deleteById(Integer... ids);
    SysResult saveItem(Item item, ItemDesc itemDesc);
    SysResult instockItemByIds(Long... ids);
    SysResult reshelfItemById(Long... ids);
    SysResult updateItemById(Item item,ItemDesc itemDesc);
    SysResult findItemDescById(Long itemId);
    List<Item> getItems();
}
