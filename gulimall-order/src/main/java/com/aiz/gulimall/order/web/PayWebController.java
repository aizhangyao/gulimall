package com.aiz.gulimall.order.web;

import com.aiz.gulimall.order.config.AlipayTemplate;
import com.aiz.gulimall.order.service.OrderService;
import com.aiz.gulimall.order.vo.PayVo;
import com.alipay.api.AlipayApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName PayWebController
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:54 2022/10/24
 * @Version 1.0
 */
@Slf4j
@Controller
public class PayWebController {
    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单:支付宝支付
     * 1、让支付页让浏览器展示
     * 2、支付成功以后，跳转到用户的订单列表页
     */
    @ResponseBody
    @GetMapping(value = "/aliPayOrder", produces = "text/html")
    public String aliPayOrder(@RequestParam("orderSn") String orderSn) throws AlipayApiException {

        PayVo payVo = orderService.getOrderPay(orderSn);
        String pay = alipayTemplate.pay(payVo);
        System.out.println("接口的响应是：" + pay);
        return pay;
    }
}
