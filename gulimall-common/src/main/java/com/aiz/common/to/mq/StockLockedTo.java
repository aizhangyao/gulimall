package com.aiz.common.to.mq;

import lombok.Data;

/**
 * @ClassName StockLockedTo
 * @Description 发送到mq消息队列的to
 * @Author ZhangYao
 * @Date Create in 14:22 2022/10/19
 * @Version 1.0
 */
@Data
public class StockLockedTo {

    /**
     * 库存工作单的id
     **/
    private Long id;

    /**
     * 工作单详情的所有信息
     **/
    private StockDetailTo detailTo;
}