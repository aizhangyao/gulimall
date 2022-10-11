package com.aiz.gulimall.product;

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
 *二.逻辑删除
 *  (一).配置全局的逻辑删除规则 (省略)
 *  (二).配置逻辑删除的组件Bean (高版本可以省略)
 *  (三).给Bean加上逻辑删除注解@TableLogic
 *
 * 三.JSR303
 *  (一).给Bean添加校验注解 @Email @NotNull在javax.validation.constraints包下
 *      @NotBlank
 *      private String name;
 *  (二).开启校验功能@Valid
 *      public R save(@Valid @RequestBody BrandEntity brand)
 *      效果：校验错误以后会有默认的响应；
 *  (三).给校验的Bean后紧跟一个BindingResult,就可以获取到校验的结果
 *  (四).分组校验(多场景的复杂校验)
 *      1.@NotBlank(message = "品牌名必须提交",groups = {AddGroup.class,UpdateGroup.class})
 *      给校验注解标注什么情况进行校验
 *      2.@Validated(AddGroup.class)
 *      3.默认没有指定分组的校验注解@Notblank,在分组校验情况@Validated(AddGroup.class)下不生效,只会在@Validated生效
 *
 * 四.统一异常处理
 *  (一).编写异常处理类，使用@ControllerAdvice
 *  (二).使用ExceptionHandler标注方法可以处理的异常
 *
 * 五.自定义校验
 *  (一).编写一个自定义的校验注解
 *     @Documented
 * @Constraint(validatedBy = {ListValueConstraintValidator.class}【可以指定多个不同的校验器，适配不同类型的校验】)
 * @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
 * @Retention(RetentionPolicy.RUNTIME)
 *  (二).编写一个自定义的校验器 ConstraintValidator
 *  (三).关联自定义的校验器和自定义的校验注解
 *
 * 六、模板引擎
 *  (一).thymeleaf-starter：关闭缓存
 *  (二).静态资源都放在static文件下就可以按照路径直接访问
 *  (三).页面都放在templates下，直接访问
 *      SpringBoot 访问项目的时候，默认会找index
 *  (四).页面修改不重启服务器实时更新
 *      (1).引入dev-tools
 *      (2).修改完页面使用Ctrl+Shift+F9重新自动编译，如果是代码配置还是推荐重启项目。
 *  可以参考WebMvcAutoConfiguration.welcomePageHandlerMapping()这个方法看。就可以理解为什么访问`localhost:8000`会转到`resources/templates/index.html`
 *
 *  七、整合Redis
 *  (一).引入data-redis-starter
 *  (二).简单配置redis的host等信息
 *  (三).使用SpringBoot自动配置好的StringRedisTemplate来操作Redis
 *  redis->Map：存放数据key,数据值value
 *
 *  八、整合redisson作为分布式锁等功能框架
 *  (一).引入依赖
 *  <dependency>
 *      <groupId>org.redisson</groupId>
 *      <artifactId>redisson</artifactId>
 *      <version>3.12.0</version>
 *  </dependency>
 *  (二).配置redisson
 *  https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95
 *
 *  九、整合SpringCache简化缓存开发
 *  (一).引入依赖
 *      spring-boot-starter-cache、spring-boot-starter-data-redis
 *  (二).写配置
 *      (1).自动配置了哪些？
 *          CacheAutoConfiguration会导入RedisCacheConfiguration
 *          自动配置好了缓存管理器RedisCacheManager
 *      (2).配置使用redis作为缓存
 *      在application.properties中配置。
 *  (三).测试使用缓存
 *      @Cacheable: Triggers cache population.触发将数据保存到缓存的操作
 *      @CacheEvict: Triggers cache eviction.触发将数据从缓存删除的操作
 *      @CachePut: Updates the cache without interfering with the method execution.不影响方法执行更新缓存
 *      @Caching: Regroups multiple cache operations to be applied on a method.组合以上多个操作
 *      @CacheConfig: Shares some common cache-related settings at class-level.在类级别共享缓存的相同配置
 *      (1).开启缓存功能@EnableCaching
 *      (2).只需要使用注解就能完成缓存操作
 *  (四).原理
 *      CacheAutoConfiguration - > RedisCacheConfiguration ->
 *      自动配置了RedisCacheManager - > 初始化所有的缓存 - > 每个缓存决定了使用什么配置
 *      - >如果redisCacheConfiguration有就用已有的，没有就用默认配置
 *      - >想改缓存的配置，只需要给容器中放一个RedisCacheConfiguration即可
 *      - >就会应用到当前RedisCacheManager管理的所有缓存分区中
 *
 */

@EnableRedisHttpSession
@EnableFeignClients(basePackages = "com.aiz.gulimall.product.feign")
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
