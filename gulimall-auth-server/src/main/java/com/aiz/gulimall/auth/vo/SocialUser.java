package com.aiz.gulimall.auth.vo;

import lombok.Data;

/**
 * @ClassName SocialUser
 * @Description 社交用户信息
 * @Author ZhangYao
 * @Date Create in 21:46 2022/10/11
 * @Version 1.0
 */
@Data
public class SocialUser {

    private String access_token;

    private String remind_in;

    private long expires_in;

    private String uid;

    private String isRealName;

}