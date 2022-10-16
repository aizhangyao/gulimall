package com.aiz.gulimall.order.feign;

import com.aiz.gulimall.order.vo.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @ClassName MemberFeignService
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 18:17 2022/10/16
 * @Version 1.0
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 查询当前用户的全部收货地址
     */
    @GetMapping(value = "/member/memberreceiveaddress/{memberId}/address")
    List<MemberAddressVo> getAddress(@PathVariable("memberId") Long memberId);
}