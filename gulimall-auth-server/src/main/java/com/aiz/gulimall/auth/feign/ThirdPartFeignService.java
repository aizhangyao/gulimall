package com.aiz.gulimall.auth.feign;

import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ThirdPartFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:21 2022/10/11
 * @Version 1.0
 */
@FeignClient("gulimall-third-party")
public interface ThirdPartFeignService {
    @GetMapping(value = "/sms/sendCode")
    R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
