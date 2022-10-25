package com.aiz.gulimall.seckill.service;

import com.aiz.gulimall.seckill.to.SeckillSkuRedisTo;

import java.util.List;

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

    List<SeckillSkuRedisTo> getCurrentSeckillSkus();

    /**
     * 根据skuId查询商品是否参加秒杀活动
     */
    SeckillSkuRedisTo getSkuSeckilInfo(Long skuId);

    /**
     * 当前商品进行秒杀（秒杀开始）
     */
    String kill(String killId, String key, Integer num) throws InterruptedException;
}
