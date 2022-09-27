package com.aiz.gulimall.ware.dao;

import com.aiz.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 23:05:10
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
