package com.aiz.gulimall.cart.exception;

import com.aiz.common.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName RuntimeExceptionHandler
 * @Description 统一异常处理
 * @Author ZhangYao
 * @Date Create in 23:22 2022/10/13
 * @Version 1.0
 */
@ControllerAdvice
public class RuntimeExceptionHandler {

    /**
     * 全局统一异常处理
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public R handler(RuntimeException exception) {
        return R.error(exception.getMessage());
    }

    @ExceptionHandler(CartExceptionHandler.class)
    public R userHandler(CartExceptionHandler exception) {
        return R.error("购物车无此商品");
    }
}