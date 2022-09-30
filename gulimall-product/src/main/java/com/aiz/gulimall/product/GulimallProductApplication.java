package com.aiz.gulimall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


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
 */
@EnableFeignClients(basePackages = "com.aiz.gulimall.product.feign")
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
