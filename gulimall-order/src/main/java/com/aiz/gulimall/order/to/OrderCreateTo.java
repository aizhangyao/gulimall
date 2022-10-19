package com.aiz.gulimall.order.to;

import com.aiz.gulimall.order.entity.OrderEntity;
import com.aiz.gulimall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName OrderCreateTo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 11:41 2022/10/19
 * @Version 1.0
 */
@Data
public class OrderCreateTo {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;
}