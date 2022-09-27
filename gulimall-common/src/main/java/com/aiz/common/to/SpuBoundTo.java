package com.aiz.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName SpuBoundTo
 * @Description
 * @Author Yao
 * @Date Create in 14:21 2020/7/22 0022
 * @Version 1.0
 */
@Data
public class SpuBoundTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
