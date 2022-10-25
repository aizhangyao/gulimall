package com.aiz.gulimall.seckill.service;

/**
 * @ClassName SeckillService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 14:11 2022/10/25
 * @Version 1.0
 */
public interface SeckillService {
    /**
     * 上架三天需要秒杀的商品
     */
    void uploadSeckillSkuLatest3Days();
}
