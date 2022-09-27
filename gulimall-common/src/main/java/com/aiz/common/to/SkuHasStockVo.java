package com.aiz.common.to;

import lombok.Data;

/**
 * @ClassName SkuHasStockVo
 * @Description
 * @Author Yao
 * @Date Create in 23:05 2020/8/2 0002
 * @Version 1.0
 */
@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}