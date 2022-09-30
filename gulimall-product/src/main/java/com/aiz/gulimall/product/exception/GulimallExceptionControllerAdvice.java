package com.aiz.gulimall.product.exception;

import com.aiz.common.exception.BizCodeEnum;
import com.aiz.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName GulimallExceptionControllerAdvice
 * @Description 集中处理所有异常
 * @Author Zhang Yao
 * @Date Create in 20:25 2020/7/4 0004
 * @Version 1.0
 */
@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "com.aiz.gulimall.product.controller")
@RestControllerAdvice(basePackages = "com.aiz.gulimall.product.controller")//相当于@ResponseBody + @ControllerAdvice
public class GulimallExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题:{},异常类型:{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError -> {
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }));
//        return R.error(400,"数据校验出现问题").put("data",errorMap);
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

}
