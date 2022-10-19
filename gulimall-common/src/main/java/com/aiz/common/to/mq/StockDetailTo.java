package com.aiz.common.to.mq;

import lombok.Data;

/**
 * @ClassName StockDetailTo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 14:23 2022/10/19
 * @Version 1.0
 */
@Data
public class StockDetailTo {

    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * 购买个数
     */
    private Integer skuNum;
    /**
     * 工作单id
     */
    private Long taskId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 锁定状态
     */
    private Integer lockStatus;

}
