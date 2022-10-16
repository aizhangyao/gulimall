package com.aiz.gulimall.order.vo;

import lombok.Data;

/**
 * @ClassName SkuStockVo
 * @Description 库存vo
 * @Author ZhangYao
 * @Date Create in 16:23 2022/10/16
 * @Version 1.0
 */
@Data
public class SkuStockVo {
    private Long skuId;
    private Boolean hasStock;
}