package com.aiz.gulimall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *  核心原理
 *  1）、@EnableRedisHttpSession导入RedisHttpSessionConfiguration配置
 *       1、给容器中添加了一个组件
 *           SessionRepository ==》【RedisOperationsSessionRepository(RedisIndexedSessionRepository)】：Redis操作session，session的增删改查封装类
 *       2、SessionRepositoryFilter==》Filter：session存储过滤器；每个请求过来都必须经过Filter
 *          1、创建的时候，就自动从容器中获取到了SessionRepository
 *          2、原生的request、response都被包装。SessionRepositoryRequestWrapper、SessionRepositoryResponseWrapper
 *          3、wrappedRequest.getSession() ==》 SessionRepository中获取到的。
 *
 *   装饰者模式
 *
 *   session自动延期
 */

@EnableRedisHttpSession //整合Redis作为session存储
@EnableFeignClients(basePackages = "com.aiz.gulimall.auth.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallAuthServerApplication.class, args);
    }

}
