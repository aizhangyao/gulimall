package com.aiz.gulimall.product.feign;

import com.aiz.common.to.SkuHasStockVo;
import com.aiz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName WareFeignService
 * @Description
 * @Author Yao
 * @Date Create in 23:22 2020/8/2 0002
 * @Version 1.0
 */
@FeignClient("gulimall-ware")
public interface WareFeignService {
    /**
     * 两种方案
     * 1.R设计的时候可以加上泛型
     *  public class R<T> extends HashMap<String, Object> {
     * 	    private T data;
     * 	}
     * 	这样的好处就是返回的就是我们想要的结果，不需要再进行数据类型转换或者解析json
     * 2.直接返回我们想要的结果
     *
     * 3.自己封装解析结果
     *
     */
    @PostMapping("/ware/waresku/hasStock")
    R<List<SkuHasStockVo>> getSkusHasStock(@RequestBody List<Long> skuIds);
}
