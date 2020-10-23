package com.jt.service;

import com.jt.pojo.Order;

public interface DubboOrderService {
    String addOrder(Order order);

    Order findPaymentByorderId(String orderId);

}
