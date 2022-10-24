package com.aiz.gulimall.order.service;

import com.aiz.gulimall.order.vo.OrderConfirmVo;
import com.aiz.gulimall.order.vo.OrderSubmitVo;
import com.aiz.gulimall.order.vo.PayAsyncVo;
import com.aiz.gulimall.order.vo.PayVo;
import com.aiz.gulimall.order.vo.SubmitOrderResponseVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiz.common.utils.PageUtils;
import com.aiz.gulimall.order.entity.OrderEntity;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 22:02:49
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 订单确认页返回需要用的数据
     */
    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    /**
     * 创建订单
     */
    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    /**
     * 按照订单号获取订单信息
     */
    OrderEntity getOrderByOrderSn(String orderSn);

    /**
     * 关闭订单
     */
    void closeOrder(OrderEntity orderEntity);

    /**
     * 获取当前订单的支付信息
     */
    PayVo getOrderPay(String orderSn);

    /**
     * 查询当前用户所有订单数据
     */
    PageUtils queryPageWithItem(Map<String, Object> params);

    /**
     *支付宝异步通知处理订单数据
     */
    String handlePayResult(PayAsyncVo asyncVo);
}

