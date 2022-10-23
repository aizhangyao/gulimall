package com.aiz.gulimall.ware.service;

import com.aiz.common.to.mq.StockLockedTo;
import com.aiz.gulimall.ware.vo.SkuHasStockVo;
import com.aiz.gulimall.ware.vo.WareSkuLockVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiz.common.utils.PageUtils;
import com.aiz.gulimall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 23:05:10
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加库存
     */
    void addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 判断是否有库存
     */
    List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds);

    /**
     * 锁定库存
     */
    boolean orderLockStock(WareSkuLockVo vo);

    /**
     * 解锁库存
     */
    void unlockStock(StockLockedTo to);

}

