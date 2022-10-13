package com.aiz.gulimall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName ThreadPoolConfigProperties
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 23:32 2022/10/13
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "gulimall.thread")
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;

}