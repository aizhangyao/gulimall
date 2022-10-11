package com.aiz.gulimall.auth.feign;

import com.aiz.common.utils.R;
import com.aiz.gulimall.auth.vo.UserLoginVo;
import com.aiz.gulimall.auth.vo.UserRegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName MemberFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:22 2022/10/11
 * @Version 1.0
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {
    @PostMapping(value = "/member/member/register")
    R register(@RequestBody UserRegisterVo vo);

    @PostMapping(value = "/member/member/login")
    R login(@RequestBody UserLoginVo vo);
}
