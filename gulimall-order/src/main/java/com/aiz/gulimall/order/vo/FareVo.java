package com.aiz.gulimall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName FareVo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 11:43 2022/10/19
 * @Version 1.0
 */
@Data
public class FareVo {
    private MemberAddressVo address;
    private BigDecimal fare;
}