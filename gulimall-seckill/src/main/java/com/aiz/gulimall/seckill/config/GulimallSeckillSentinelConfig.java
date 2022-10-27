package com.aiz.gulimall.seckill.config;

import com.aiz.common.exception.BizCodeEnum;
import com.aiz.common.utils.R;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GulimallSeckillSentinelConfig
 * @Description 秒杀限流异常处理程序 - 自定义流控响应
 * @Author ZhangYao
 * @Date Create in 14:54 2022/10/26
 * @Version 1.0
 */
@Configuration
public class GulimallSeckillSentinelConfig implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        R error = R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMsg());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(JSON.toJSONString(error));
    }
}