package com.aiz.gulimall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName FareVo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 01:06 2022/10/17
 * @Version 1.0
 */
@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}
