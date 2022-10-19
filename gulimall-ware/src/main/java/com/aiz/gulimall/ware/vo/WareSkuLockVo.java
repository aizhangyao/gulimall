package com.aiz.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName WareSkuLockVo
 * @Description 锁定库存的vo
 * @Author ZhangYao
 * @Date Create in 11:57 2022/10/19
 * @Version 1.0
 */
@Data
public class WareSkuLockVo {
    private String orderSn;
    /**
     * 需要锁住的所有库存信息
     **/
    private List<OrderItemVo> locks;
}
