package com.aiz.gulimall.order.web;

import com.aiz.gulimall.order.service.OrderService;
import com.aiz.gulimall.order.vo.OrderConfirmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName OrderWebController
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 16:18 2022/10/16
 * @Version 1.0
 */
@Controller
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    /**
     * 去结算确认页
     */
    @GetMapping(value = "/toTrade")
    public String toTrade(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {

        OrderConfirmVo confirmVo = orderService.confirmOrder();

        model.addAttribute("confirmOrderData", confirmVo);
        //展示订单确认的数据

        return "confirm";
    }
}
