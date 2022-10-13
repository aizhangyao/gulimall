package com.aiz.gulimall.cart.to;

import lombok.Data;

/**
 * @ClassName UserInfoTo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 23:11 2022/10/13
 * @Version 1.0
 */
@Data
public class UserInfoTo {
    private Long userId;
    private String userKey;
    /**
     * 是否临时用户
     */
    private Boolean tempUser = false;
}