package com.aiz.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName NoStockException
 * @Description 无库存抛出的异常
 * @Author ZhangYao
 * @Date Create in 11:29 2022/10/19
 * @Version 1.0
 */
public class NoStockException extends RuntimeException {
    @Getter
    @Setter
    private Long skuId;

    public NoStockException(Long skuId) {
        super("商品id："+ skuId + "库存不足！");
    }

    public NoStockException(String msg) {
        super(msg);
    }

}