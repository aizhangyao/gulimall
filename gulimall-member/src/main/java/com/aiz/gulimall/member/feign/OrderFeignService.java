package com.aiz.gulimall.member.feign;

import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @ClassName OrderFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 17:50 2022/10/24
 * @Version 1.0
 */
@FeignClient("gulimall-order")
public interface OrderFeignService {
    /**
     * 分页查询当前登录用户的所有订单信息
     */
    @PostMapping("/order/order/listWithItem")
    R listWithItem(@RequestBody Map<String, Object> params);
}
