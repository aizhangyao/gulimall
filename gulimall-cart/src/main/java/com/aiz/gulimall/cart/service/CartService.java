package com.aiz.gulimall.cart.service;

import com.aiz.gulimall.cart.vo.CartItemVo;
import com.aiz.gulimall.cart.vo.CartVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName CartService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 10:29 2022/10/13
 * @Version 1.0
 */
public interface CartService {

    /**
     * 将商品添加至购物车
     */
    CartItemVo addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    /**
     * 获取购物车某个购物项
     */
    CartItemVo getCartItem(Long skuId);

    /**
     * 获取购物车里面的信息
     */
    CartVo getCart() throws ExecutionException, InterruptedException;

    /**
     * 清空购物车的数据
     */
    void clearCartInfo(String cartKey);

    /**
     * 勾选购物项
     */
    void checkItem(Long skuId, Integer check);

    /**
     * 改变商品数量
     */
    void changeItemCount(Long skuId, Integer num);


    /**
     * 删除购物项
     */
    void deleteIdCartInfo(Integer skuId);

    List<CartItemVo> getUserCartItems();
}
