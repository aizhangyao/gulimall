package com.aiz.gulimall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 一.整合Mybatis-Plus
 *  (一).导入依赖
 *       <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-boot-starter</artifactId>
 *             <version>3.2.0</version>
 *         </dependency>
 *  (二).配置
 *      1.配置数据源
 *          (1).导入数据库驱动。
 *          (2).在application.yml配置数据源相关信息
 *      2.配置MyBatis-Plus
 *          (1).使用@MapperScan
 *          (2).高速MyBatis-Plus，sql映射文件位置

 * 二.使用RabbitMQ
 *  (一)、引入amqp场景：RabbitAutoConfiguration就会自动生效
 *  (二)、给容器中自动配置了
 *      RabbitTemplate、AmqpAdmin、CachingConnectionFactory、RabbitMessagingTemplate
 *      所有的属性都是@ConfigurationProperties(prefix = "spring.rabbitmq")
 *
 *  (三)、给配置文件中配置spring.rabbitmq信息
 *  (四)、@EnableRabbit:@EnableXxxx：开启功能
 *  (五)、监听消息：使用@RabbitListener；必须有@EnableRabbit
 *      @RabbitListener：类+方法上
 *      @RabbitHandler：标在方法上
 */

@EnableRedisHttpSession
@EnableRabbit
@MapperScan("com.aiz.gulimall.order.dao")
@EnableFeignClients(basePackages = "com.aiz.gulimall.order.feign")
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallOrderApplication.class, args);
    }

}
