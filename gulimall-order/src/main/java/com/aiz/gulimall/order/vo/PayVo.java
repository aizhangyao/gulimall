package com.aiz.gulimall.order.vo;

import lombok.Data;

/**
 * @ClassName PayVo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:37 2022/10/24
 * @Version 1.0
 */
@Data
public class PayVo {

    private String out_trade_no; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private String total_amount;  // 付款金额 必填
    private String body; // 商品描述 可空
}

