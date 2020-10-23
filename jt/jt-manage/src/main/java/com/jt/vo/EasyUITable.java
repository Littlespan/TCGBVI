package com.jt.vo;

import com.jt.pojo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/25 19:20
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITable implements Serializable {
    private Integer total;
    private List<Item> rows;
}
