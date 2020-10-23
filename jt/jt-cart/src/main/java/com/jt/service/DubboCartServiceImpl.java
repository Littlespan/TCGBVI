package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/21 14:33
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Service
public class DubboCartServiceImpl implements DubboCartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCart(Long userId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("user_id",userId);
        return cartMapper.selectList(qw);
    }

    @Override
    public void updateCartNum(Cart cart) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("user_id",cart.getUserId())
                .eq("item_id",cart.getItemId());
        Cart updatedCart = new Cart();
        updatedCart.setNum(cart.getNum()).setUpdated(new Date());
        cartMapper.update(cart,qw);
    }

    @Override
    public void deleteByItemId(Cart cart) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("user_id",cart.getUserId())
                .eq("item_id", cart.getItemId());
        cartMapper.delete(qw);
    }

    @Override
    public void saveCart(Cart cart) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("user_id",cart.getUserId())
                .eq("item_id",cart.getItemId());
        Cart cartDB = cartMapper.selectOne(qw);
        if(cartDB == null){
            cartMapper.insert(cart);
        }else{
            cart.setNum(cart.getNum() + cartDB.getNum());
            cartMapper.update(cart,qw);
        }

    }
}
