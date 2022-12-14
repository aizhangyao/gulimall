package com.aiz.gulimall.product.feign;

import com.aiz.common.utils.R;
import com.aiz.gulimall.product.fallback.SeckillFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName SeckillFeignService
 * @Description 熔断 => 远程调用出现问题，fallback = SeckillFeignServiceFallBack.class做默认回调
 * @Author ZhangYao
 * @Date Create in 15:44 2022/10/25
 * @Version 1.0
 */
@FeignClient(value = "gulimall-seckill",fallback = SeckillFeignServiceFallBack.class)
public interface SeckillFeignService {

    /**
     * 根据skuId查询商品是否参加秒杀活动
     */
    @GetMapping(value = "/sku/seckill/{skuId}")
    R getSkuSeckillInfo(@PathVariable("skuId") Long skuId);

}