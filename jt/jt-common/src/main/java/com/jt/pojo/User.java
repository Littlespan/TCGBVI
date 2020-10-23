package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/17 9:44
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Data
@Accessors(chain = true)
@TableName("tb_user")
public class User extends BasePojo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;

}
