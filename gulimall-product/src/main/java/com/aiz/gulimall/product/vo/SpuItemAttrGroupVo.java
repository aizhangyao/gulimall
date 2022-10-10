package com.aiz.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName SpuItemAttrGroupVo
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 16:26 2022/10/10
 * @Version 1.0
 */

@Data
@ToString
public class SpuItemAttrGroupVo {

    private String groupName;

    private List<Attr> attrs;

}
