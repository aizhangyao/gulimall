package com.aiz.gulimall.ware.feign;

import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName OrderFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 20:29 2022/10/23
 * @Version 1.0
 */
@FeignClient("gulimall-order")
public interface OrderFeignService {

    @GetMapping(value = "/order/order/status/{orderSn}")
    R getOrderStatus(@PathVariable("orderSn") String orderSn);

}