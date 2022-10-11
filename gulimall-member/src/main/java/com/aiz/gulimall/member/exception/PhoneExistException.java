package com.aiz.gulimall.member.exception;

/**
 * @ClassName PhoneExistException
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:50 2022/10/11
 * @Version 1.0
 */
public class PhoneExistException extends RuntimeException {

    public PhoneExistException() {
        super("存在相同的手机号");
    }
}
