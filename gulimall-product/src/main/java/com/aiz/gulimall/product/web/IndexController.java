package com.aiz.gulimall.product.web;

import com.aiz.gulimall.product.entity.CategoryEntity;
import com.aiz.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName IndexController
 * @Description 跳转首页
 * @Author Zhang Yao
 * @Date Create in 21:50 2020/8/13 0013
 * @Version 1.0
 */

@Controller
public class IndexController {
    @Autowired
    CategoryService categoryService;

    /* 首页面跳转 */
    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){

        // TODO 1.查出所有1级分类
        List<CategoryEntity> categoryEntityList = categoryService.getLeve1Categorys();

        model.addAttribute("categorys", categoryEntityList);

        /*
         *  默认前缀spring:thymeleaf:prefix = classpath:/templates/
         *  默认后缀spring:thymeleaf:prefix = .html
         *  视图解析器进行拼串：classpath:/templates/ + 返回值 + .html
         */
        return "index";
    }


}
