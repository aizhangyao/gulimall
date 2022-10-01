package com.aiz.gulimall.search.service;

import com.aiz.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName ProductSaveService
 * @Description
 * @Author Yao
 * @Date Create in 23:59 2020/8/2 0002
 * @Version 1.0
 */
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
