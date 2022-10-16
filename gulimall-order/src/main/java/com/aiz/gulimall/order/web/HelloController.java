package com.aiz.gulimall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 00:27 2022/10/16
 * @Version 1.0
 */
@Controller
public class HelloController {

    @GetMapping(value = "/{page}.html")
    public String listPage(@PathVariable("page") String page) {
        return page;
    }
    
}
