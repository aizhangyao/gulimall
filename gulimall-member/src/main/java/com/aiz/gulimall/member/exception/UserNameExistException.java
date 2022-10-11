package com.aiz.gulimall.member.exception;

/**
 * @ClassName UsernameException
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 15:51 2022/10/11
 * @Version 1.0
 */
public class UserNameExistException extends RuntimeException {
    public UserNameExistException() {
        super("存在相同的用户名");
    }
}