package com.aiz.gulimall.product.fallback;

import com.aiz.common.exception.BizCodeEnum;
import com.aiz.common.utils.R;
import com.aiz.gulimall.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

/**
 * @ClassName SeckillFeignServiceFallBack
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:45 2022/10/25
 * @Version 1.0
 */
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMsg());
    }
}