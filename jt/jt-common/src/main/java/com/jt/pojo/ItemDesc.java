package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/29 11:42
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */

@TableName("tb_item_desc")
@Data
@Accessors(chain = true)
public class ItemDesc extends BasePojo{

    private static final long serialVersionUID = 528978119945987467L;
    @TableId
    private Long itemId;
    private String itemDesc;

}
