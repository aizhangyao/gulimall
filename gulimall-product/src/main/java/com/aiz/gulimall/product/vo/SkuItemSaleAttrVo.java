package com.aiz.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName SkuItemSaleAttrVo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 16:25 2022/10/10
 * @Version 1.0
 */

@Data
@ToString
public class SkuItemSaleAttrVo {

    private Long attrId;

    private String attrName;

    private List<AttrValueWithSkuIdVo> attrValues;
}
