package com.jt.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/13 15:25
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public class ObjectMapperUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static String toJson(Object object){
        if(object == null){
            throw new RuntimeException("传递的数据为null,请检查");
        }
        try{
            return MAPPER.writeValueAsString(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <T> T toObj(String json, Class<T> target){
        if(StringUtils.isEmpty(json) || target == null){
            throw new RuntimeException("参数不能为null");
        }
        try{
            return MAPPER.readValue(json, target);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
