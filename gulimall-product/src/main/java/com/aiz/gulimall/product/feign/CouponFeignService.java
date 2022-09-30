package com.aiz.gulimall.product.feign;

import com.aiz.common.to.SkuReductionTo;
import com.aiz.common.to.SpuBoundTo;
import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName CouponFeignService
 * @Description
 * @Author Yao
 * @Date Create in 14:14 2020/7/22 0022
 * @Version 1.0
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 1.CouponFeignService.saveSpuBounds(supBoundTo);
     *  1).@RequestBody将这个对象转为json。
     *  2).找到gulimall-coupon服务，给/coupon/spubounds/save发送请求。
     *      将上一步转的json放在请求体位置，发送请求。
     *  3).对方服务收到请求。请求体里有json数据。(@RequestBody SpuBoundsEntity spuBounds)：将请求体的json转为SpuBoundsEntity。
     * 只要json数据模型是兼容的。双方服务无需使用同一个To对象
     * @param supBoundTo
     * @return
     */
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo supBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}