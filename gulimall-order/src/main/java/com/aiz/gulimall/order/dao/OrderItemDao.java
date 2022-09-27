package com.aiz.gulimall.order.dao;

import com.aiz.gulimall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 22:02:49
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
