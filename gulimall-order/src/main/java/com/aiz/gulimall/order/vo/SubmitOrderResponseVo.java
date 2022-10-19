package com.aiz.gulimall.order.vo;

import com.aiz.gulimall.order.entity.OrderEntity;
import lombok.Data;

/**
 * @ClassName SubmitOrderResponseVo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 11:28 2022/10/19
 * @Version 1.0
 */
@Data
public class SubmitOrderResponseVo {
    private OrderEntity order;
    /** 错误状态码 **/
    private Integer code;
}