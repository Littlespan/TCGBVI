package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/21 14:16
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Data
@TableName("tb_cart")
@Accessors(chain = true)
public class Cart extends BasePojo {

    private static final long serialVersionUID = -630294479760923569L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long itemId;
    private String itemTitle;
    private String itemImage;
    private Long itemPrice;
    private Integer num;


}
