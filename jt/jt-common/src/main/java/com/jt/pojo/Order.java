package com.jt.pojo;


import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_order")
@Data
@Accessors(chain=true)
public class Order extends BasePojo{

    private static final long serialVersionUID = 7271265533260538110L;

    @TableField(exist=false)	//入库操作忽略该字段
	private OrderShipping orderShipping;
								//封装订单商品信息  一对多
	@TableField(exist=false)	//入库操作忽略该字段
	private List<OrderItem> orderItems;
	
	@TableId
    private String orderId; //订单号：时间戳
    private String payment;
    private Integer paymentType;
    private String postFee;
    private Integer status;
    private Date paymentTime;
    private Date consignTime;
    private Date endTime;
    private Date closeTime;
    private String shippingName;
    private String shippingCode;
    private Long userId;
    private String buyerMessage;
    private String buyerNick;
    private Integer buyerRate;

}