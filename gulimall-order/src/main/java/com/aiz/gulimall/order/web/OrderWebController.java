package com.aiz.gulimall.order.web;

import com.aiz.common.exception.NoStockException;
import com.aiz.gulimall.order.service.OrderService;
import com.aiz.gulimall.order.vo.OrderConfirmVo;
import com.aiz.gulimall.order.vo.OrderSubmitVo;
import com.aiz.gulimall.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /**
     * 下单功能
     */
    @PostMapping(value = "/submitOrder")
    public String submitOrder(OrderSubmitVo vo, Model model, RedirectAttributes attributes) {
        try {
            SubmitOrderResponseVo responseVo = orderService.submitOrder(vo);
            //下单成功来到支付选择页
            //下单失败回到订单确认页重新确定订单信息
            if (responseVo.getCode() == 0) {
                //成功
                model.addAttribute("submitOrderResp", responseVo);
                return "pay";
            } else {
                String msg = "下单失败";
                switch (responseVo.getCode()) {
                    case 1: msg += "令牌订单信息过期，请刷新再次提交"; break;
                    case 2: msg += "订单商品价格发生变化，请确认后再次提交"; break;
                    case 3: msg += "库存锁定失败，商品库存不足"; break;
                }
                attributes.addFlashAttribute("msg", msg);
                return "redirect:http://order.gulimall.com/toTrade";
            }
        } catch (Exception e) {
            if (e instanceof NoStockException) {
                attributes.addFlashAttribute("msg", e.getMessage());
            }
            return "redirect:http://order.gulimall.com/toTrade";
        }
    }
}
