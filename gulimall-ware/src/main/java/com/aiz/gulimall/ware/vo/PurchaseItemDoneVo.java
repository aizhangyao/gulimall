package com.aiz.gulimall.ware.vo;

import lombok.Data;

/**
 * @ClassName PurchaseItemDoneVo
 * @Description
 * @Author Yao
 * @Date Create in 13:16 2020/7/23 0023
 * @Version 1.0
 */
@Data
public class PurchaseItemDoneVo {
    //{itemId:1,status:4,reason:""}
    private Long itemId;
    private Integer status;
    private String reason;
}
