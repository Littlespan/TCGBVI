package com.jt.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemDesc;
import com.jt.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 14:35
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@RestController
public class JSONPController {

    @RequestMapping("/web/testJSONP")
    public JSONPObject testJSONP(String callback){
        ItemDesc itemdesc = new ItemDesc();
        itemdesc.setItemId(1000L).setItemDesc("JSONP测试");
        String json = ObjectMapperUtil.toJson(itemdesc);
        return new JSONPObject(callback, json);
    }
}
