package com.aiz.gulimall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName PurchaseDoneVo
 * @Description
 * @Author Yao
 * @Date Create in 13:16 2020/7/23 0023
 * @Version 1.0
 */
@Data
public class PurchaseDoneVo {

    @NotNull
    private Long id;//采购单id

    private List<PurchaseItemDoneVo> items;
}