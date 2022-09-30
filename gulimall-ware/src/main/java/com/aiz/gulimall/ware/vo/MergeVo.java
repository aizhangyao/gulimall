package com.aiz.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName MergeVo
 * @Description
 * @Author Yao
 * @Date Create in 12:11 2020/7/23 0023
 * @Version 1.0
 */
@Data
public class MergeVo {
    /**
     * {
     *   purchaseId: 1, //整单id
     *   items:[1,2,3,4] //合并项集合
     * }
     */
    private Long purchaseId;
    private List<Long> items;
}

