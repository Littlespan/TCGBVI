package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Thread.UserThreadLocal;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/21 14:41
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Controller
@RequestMapping("/cart/")
public class CartController {
    @Reference(timeout = 3000,check = false)
    private DubboCartService cartService;

    @RequestMapping("show")
    public String PageShow(Model model,HttpServletRequest request){
//        Long userId = ((User)request.getAttribute("user")).getId();
        Long userId = UserThreadLocal.get().getId();
        model.addAttribute("cartList", cartService.findCart(userId));
        return "cart";
    }

    @RequestMapping("update/num/{itemId}/{num}")
    @ResponseBody
    public SysResult updateNum(Cart cart, HttpServletRequest request){
//        Long userId = ((User)request.getAttribute("user")).getId();
        Long userId = UserThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.updateCartNum(cart);
        return SysResult.success();
    }

    @RequestMapping("delete/{itemId}")
    public String deleteByItemId(Cart cart,HttpServletRequest request){
//        Long userId = ((User)request.getAttribute("user")).getId();
        Long userId = UserThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.deleteByItemId(cart);
        return "redirect:/cart/show.html";
    }

    @RequestMapping("add/{itemId}")
    public String saveCart(Cart cart,HttpServletRequest request){
//        Long userId = ((User)request.getAttribute("user")).getId();
        Long userId = UserThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.saveCart(cart);
        return "redirect:/cart/show.html";
    }

}
