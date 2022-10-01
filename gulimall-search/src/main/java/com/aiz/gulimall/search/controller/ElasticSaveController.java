package com.aiz.gulimall.search.controller;

import com.aiz.common.exception.BizCodeEnum;
import com.aiz.common.to.es.SkuEsModel;
import com.aiz.common.utils.R;
import com.aiz.gulimall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName ElasticSaveController
 * @Description
 * @Author Yao
 * @Date Create in 23:56 2020/8/2 0002
 * @Version 1.0
 */

@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {
    @Autowired
    ProductSaveService productSaveService;

    //上架商品
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels){
        boolean b = false;
        try {
            b = productSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            log.error("ElasticSaveController商品上架错误：{}",e);
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPYION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPYION.getMsg());
        }
        if(!b){
            return R.ok();
        }else{
            return R.error();
        }

    }

}
