package com.jt.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/13 14:39
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public class JsonTest {
    @Test
    public void JsonTest1() throws JsonProcessingException {
        ItemDesc itemDesc = new ItemDesc();
        ObjectMapper objectMapper = new ObjectMapper();
        itemDesc.setItemId(100L).setItemDesc("abc").setCreated(new Date()).setUpdated(new Date());
        String json = objectMapper.writeValueAsString(itemDesc);
        System.out.println(json);
        System.out.println(objectMapper.readValue(json, ItemDesc.class));
    }
}
