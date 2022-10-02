package com.aiz.gulimall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName Catelog2Vo
 * @Description
 * @Author zhangyao
 * @Date Create in 10:22 上午 2020/9/22
 * @Version 1.0
 */

/**
 * 二级分类vo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Catalog2Vo {
    private String catalog1Id;//1级父分类
    private List<Catalog3Vo> catalog3List;//三级子分类
    private String id;
    private String name;

    /**
     * 三级分类vo
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Catalog3Vo {
        private String catalog2Id;//父分类，2级父分类
        private String id;
        private String name;
    }

}
