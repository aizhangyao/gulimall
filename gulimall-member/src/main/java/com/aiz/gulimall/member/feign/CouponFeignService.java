package com.aiz.gulimall.member.feign;

import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是一个声明式的远程调用
 *
 * @author ZhangYao
 * @ClassName CouponFeignService
 * @email zhangyao_xz@foxmail.com
 * @Date Create in 13:17 2022/9/28
 * @Version 1.0
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/coupon/member/list")
    R memberCoupons();
}