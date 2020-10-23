package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Thread.UserThreadLocal;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/22 9:13
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Controller
@RequestMapping("/order/")
public class OrderController {
    @Reference(timeout = 3000, check = false)
    private DubboOrderService orderService;
    @Reference(timeout = 3000, check = false)
    private DubboCartService cartService;

    @RequestMapping("create")
    public String create(Model model) {
        Long userId = UserThreadLocal.get().getId();
        model.addAttribute("carts", cartService.findCart(userId));
        return "order-cart";
    }

    @RequestMapping("submit")
    @ResponseBody
    private SysResult submit(Order order) {
        order.setUserId(UserThreadLocal.get().getId());
        String orderId = orderService.addOrder(order);
        if (StringUtils.isEmpty(orderId)) {
            return SysResult.fail();
        } else
            return SysResult.success(orderId);
    }

    @RequestMapping("success")
    public String success(String id, Model model) {
        Order order = orderService.findPaymentByorderId(id);
        model.addAttribute("order", order);
        model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "success";
    }
}
