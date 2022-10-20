package com.aiz.gulimall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
 *
 *  本地事务失效问题
 *  同一个对象内方法互相调用默认失败，原因 绕过了代理对象，事务使用代理对象来控制的。
 *  解决：使用代理对象来调用事务方法
 *  1)、引入aop-stater; spring-boot-starter-aop; 引入了aspectj
 *  2)、@EnableAspectJAutoProxy(exposeProxy = true)开启aspectj动态代理功能。以后所有的动态代理都是aspectj创建的(即使没有接口也可以创建动态代理)
 *      对外暴露代理对象
 *  3)、本类互相调用调用对象
 *         OrderServiceImpl orderService = (OrderServiceImpl) AopContext.currentProxy();
 *         orderService.b();
 *         orderService.c();
 *
 * Seata控制分布式事务
 *  1）、每一个微服务必须创建undo_Log
 *  2）、安装事务协调器：seate-server
 *  3）、整合
 *      1、导入依赖 spring-cloud-starter-alibaba-seata seata-all-0.9.0
 *      2、解压并启动seata-server：
 *          registry.conf:注册中心配置    修改 registry ： nacos
 *      3、所有想要用到分布式事务的微服务使用seata DataSourceProxy 代理自己的数据源
 *      4、每个微服务，都必须导入   registry.conf   file.conf
 *          vgroup_mapping.{application.name}-fescar-server-group = "default"
 *      5、启动测试分布式事务
 *      6、给分布式大事务的入口标注@GlobalTransactional
 *      7、每一个远程的小事务用@Trabsactional
 */

@EnableAspectJAutoProxy(exposeProxy = true)
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
