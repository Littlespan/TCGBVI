package com.jt;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/19 10:32
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public class HttpClientTest {

    @Test
    public void testGet() throws IOException {
        String url = "http://www.baidu.com";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if(httpResponse.getStatusLine().getStatusCode()==200){
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity,"utf-8");
            System.out.println(result);
        }else{
            System.out.println(httpResponse.getStatusLine().getStatusCode());
        }
    }
}
