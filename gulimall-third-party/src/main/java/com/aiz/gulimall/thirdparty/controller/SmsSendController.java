package com.aiz.gulimall.thirdparty.controller;

import com.aiz.common.utils.R;
import com.aiz.gulimall.thirdparty.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SmsSendController
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:17 2022/10/11
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/sms")
public class SmsSendController {

    @Autowired
    private SmsComponent smsComponent;

    /**
     * 提供给别的服务进行调用
     */
    @GetMapping(value = "/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        //发送验证码
        smsComponent.sendSmsCode(phone, code);

        return R.ok();
    }
}
