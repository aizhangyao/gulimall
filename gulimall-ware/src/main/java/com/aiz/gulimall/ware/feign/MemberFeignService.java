package com.aiz.gulimall.ware.feign;

import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MemberFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 01:09 2022/10/17
 * @Version 1.0
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 根据id获取用户地址信息å
     */
    @RequestMapping("/member/memberreceiveaddress/info/{id}")
    R info(@PathVariable("id") Long id);
}