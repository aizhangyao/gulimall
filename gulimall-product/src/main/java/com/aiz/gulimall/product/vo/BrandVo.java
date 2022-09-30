package com.aiz.gulimall.product.vo;

import lombok.Data;

/**
 * @ClassName BrandVo
 * @Description
 * @Author Yao
 * @Date Create in 16:05 2020/7/21 0021
 * @Version 1.0
 */
@Data
public class BrandVo {
    /**
     * [{
     *  brandId": 0,
     * "brandName": "string",
     * }]
     */
    private Long brandId;
    private String brandName;
}
