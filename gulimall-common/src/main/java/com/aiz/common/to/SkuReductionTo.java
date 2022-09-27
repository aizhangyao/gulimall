package com.aiz.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SkuReductionTo
 * @Description
 * @Author Yao
 * @Date Create in 15:03 2020/7/22 0022
 * @Version 1.0
 */
@Data
public class SkuReductionTo {
    private Long skuId;

    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}
