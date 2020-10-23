package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/25 20:12
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */

@TableName("tb_item_cat")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain=true)
public class ItemCat extends BasePojo{
    private static final long serialVersionUID = -4067493419718014413L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;

}
