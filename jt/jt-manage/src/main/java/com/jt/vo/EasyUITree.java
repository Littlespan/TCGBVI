package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/27 16:47
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree implements Serializable {
    private static final long serialVersionUID = -7639467787540631573L;
    private Long id;
    private String text;
    private String state;
}
