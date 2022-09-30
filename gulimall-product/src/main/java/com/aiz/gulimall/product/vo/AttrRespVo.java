package com.aiz.gulimall.product.vo;

import lombok.Data;

/**
 * @ClassName AttrRespVo
 * @Description 响应数据
 * @Author ZhangYao
 * @Date Create in 15:25 2020/7/7 0007
 * @Version 1.0
 */
@Data
public class AttrRespVo extends AttrVo{

    /**
     * 所属分类名字
     */
    private String catelogName;

    /**
     * 所属分组名字
     */
    private String groupName;

    private Long[] catelogPath;

}
