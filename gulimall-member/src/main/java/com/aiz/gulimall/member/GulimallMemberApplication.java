package com.aiz.gulimall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 1.想要远程调用别的服务
 *  1).引入open-feign依赖
 *  2).编写一个接口，告诉SpringCloud这个接口需要远程调用远程服务
 *     (1).在接口中每一个方法都是调用哪个远程服务的哪个请求
 *  3).开启远程调用功能
 */
@EnableRedisHttpSession
@EnableFeignClients(basePackages = "com.aiz.gulimall.member.feign")
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallMemberApplication.class, args);
    }

}
