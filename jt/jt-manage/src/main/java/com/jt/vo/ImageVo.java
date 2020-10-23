package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/29 16:01
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageVo {
    private Integer error;
    private String url;
    private Integer width;
    private Integer height;

    public static ImageVo fail(){
        return new ImageVo(1,null,null,null);
    }

    public static ImageVo success(String url , Integer width , Integer height){
        return new ImageVo(0,url,width,height);
    }
}
