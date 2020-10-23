package com.jt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;

@Service
public class DubboOrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;


    @Override
    @Transactional
    public String addOrder(Order order) {
        String orderId = order.getUserId()+""+System.currentTimeMillis();
        order.setOrderId(orderId).setStatus(1);
        orderMapper.insert(order);

        OrderShipping orderShipping =order.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShippingMapper.insert(orderShipping);

        for (OrderItem orderItem:order.getOrderItems()) {
            orderItem.setOrderId(orderId);
            orderItemMapper.insert(orderItem);
        }

        System.out.println("订单入库成功！");
    return orderId;
    }

    @Override
    public Order findPaymentByorderId(String orderId) {
        QueryWrapper QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("order_id",orderId);
        Order order = orderMapper.selectOne(QueryWrapper);
        List orderItems = orderItemMapper.selectList(QueryWrapper);
        OrderShipping orderShipping = orderShippingMapper.selectOne(QueryWrapper);
        order.setOrderShipping(orderShipping).setOrderItems(orderItems);
        return order;
    }
}
