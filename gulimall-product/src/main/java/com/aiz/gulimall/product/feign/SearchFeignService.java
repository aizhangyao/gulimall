package com.aiz.gulimall.product.feign;

import com.aiz.common.to.es.SkuEsModel;
import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName SearchFeignService
 * @Description
 * @Author Yao
 * @Date Create in 9:38 2020/8/3 0003
 * @Version 1.0
 */
@FeignClient("gulimall-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
