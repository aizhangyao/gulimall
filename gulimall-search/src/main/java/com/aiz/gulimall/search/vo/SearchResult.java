package com.aiz.gulimall.search.vo;

import com.aiz.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SearchResult
 * @Description 返回给页面的所有信息
 * @Author Yao
 * @Date Create in 1:14 上午 2020/11/29
 * @Version 1.0
 */
@Data
public class SearchResult {

    /**
     * 查询到的所有商品信息
     */
    private List<SkuEsModel> products;

    /**
     * 以下是分页信息
     */
    private Integer pageNum;//当前页码
    private Long total;//总记录数
    private Integer totalPages;//总页码

    /**
     * 当前查询到的结果，所有涉及到的品牌
     */
    private List<BrandVo> brands;

    /**
     * 当前查询到的结果，所有涉及到的分类
     */
    private List<CatalogVo> catalogs;

    /**
     * 当前查询到的结果，所有涉及到的所有属性
     */
    private List<AttrVo> attrs;

    //以上是返回给页面的所有信息


    @Data
    public static class BrandVo{
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class CatalogVo{
        private Long catalogId;
        private String catalogName;
    }

    @Data
    public static class AttrVo{
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }

}
