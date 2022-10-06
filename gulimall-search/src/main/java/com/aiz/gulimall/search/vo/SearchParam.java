package com.aiz.gulimall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SearchParam
 * @Description 封装页面所有可能传递过来的查询条件
 *              catalog3Id=225&keyword=小米&sort=saleCount_asc&hasStock=1&
 * @Author Yao
 * @Date Create in 11:19 下午 2020/11/28
 * @Version 1.0
 */
@Data
public class SearchParam {
    /**
     * 检索关键字
     */
    private String keyword;//页面传递过来的全文匹配关键字
    //三级分类ID
    private Long catalog3Id;

    /**
     * sort=saleCount_asc/desc
     * sort=hotScore_asc/desc
     * sort=skuPrice_asc/desc
     */
    private String sort;//排序条件(saleCount（销量）、hotScore（热度分）、skuPrice（价格）)

    /**
     * 好多的过滤条件
     * hasStock(是否有货)、skuPrice区间、brandId、catalog3Id、attrs
     * hasStock=0/1
     * skuPrice=1_500/_500/500
     * brandId=1
     * attrs=2_5寸:6寸
     */
    //是否有库存(0表示无库存，1表示有库存)
    private Integer hasStock = 1;
    //价格区间查询
    private String skuPrice;
    //按照品牌进行查询，可以多选
    private List<Long> brandId;
    //按照属性进行筛选
    private List<String> attrs;

    //页码
    private Integer pageNum = 1;

    /**
     * 原生的所有查询条件
     */
    private String _queryString;
}
