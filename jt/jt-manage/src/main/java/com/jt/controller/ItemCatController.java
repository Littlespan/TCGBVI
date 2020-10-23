package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/25 20:25
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */

@RestController
@RequestMapping("/item/cat/")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("queryItemName")
    public String findItemCatName(Long itemCatId){ //商品分类名称
        return itemCatService.findItemCatNameById(itemCatId);
    }

    @RequestMapping("list")
    public List<EasyUITree> findItemCatList(@RequestParam(value="id",defaultValue = "0") Long parentId){
//        Long parentId=(id==null?0L:id);
//        return itemCatService.findItemCatListCache(parentId);
        return itemCatService.findItemCat(parentId);
    }

}
