package com.aiz.gulimall.product.dao;

import com.aiz.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 22:41:59
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
