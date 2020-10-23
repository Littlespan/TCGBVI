package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.util.ObjectMapperUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/16 17:04
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Controller
public class indexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/getItems")
    @ResponseBody
    public List<Item> getItems() throws IOException {
        List<Item> list = new ArrayList<>();
        String url = "http://manage.jt.com/getItems";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if(httpResponse.getStatusLine().getStatusCode()==200){
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            list = ObjectMapperUtil.toObj(result, list.getClass());
            return list;
        }else{
            return null;
        }

    }
}
