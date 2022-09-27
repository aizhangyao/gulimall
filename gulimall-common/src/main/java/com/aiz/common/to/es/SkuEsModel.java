package com.aiz.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SkuEsModel
 * @Description
 * @Author Yao
 * @Date Create in 17:16 2020/8/2 0002
 * @Version 1.0
 */
@Data
public class SkuEsModel {
    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<Attr> attrs;

    @Data
    public static class Attr{
        private Long attrId;
        private String attrName;
        private String attrValue;
    }
}
