package com.aiz.gulimall.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName GulimallWebConfig
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 00:08 2022/10/11
 * @Version 1.0
 */
@Configuration
public class GulimallWebConfig implements WebMvcConfigurer {

    /**
     * ·
     * 视图映射:发送一个请求，直接跳转到一个页面
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/reg.html").setViewName("reg");
    }

}