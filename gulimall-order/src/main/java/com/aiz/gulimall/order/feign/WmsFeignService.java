package com.aiz.gulimall.order.feign;

import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName WmsFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 18:23 2022/10/16
 * @Version 1.0
 */
@FeignClient("gulimall-ware")
public interface WmsFeignService {
    /**
     * 查询sku是否有库存
     */
    @PostMapping(value = "/ware/waresku/hasStock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);
}
