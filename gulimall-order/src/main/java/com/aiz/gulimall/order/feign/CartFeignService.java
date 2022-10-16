package com.aiz.gulimall.order.feign;

import com.aiz.gulimall.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName CartFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 18:21 2022/10/16
 * @Version 1.0
 */
@FeignClient("gulimall-cart")
public interface CartFeignService {

    /**
     * 查询当前用户购物车选中的商品项
     */
    @GetMapping(value = "/currentUserCartItems")
    List<OrderItemVo> getCurrentCartItems();

}
