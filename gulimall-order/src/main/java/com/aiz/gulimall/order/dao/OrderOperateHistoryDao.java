package com.aiz.gulimall.order.dao;

import com.aiz.gulimall.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 22:02:49
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseMapper<OrderOperateHistoryEntity> {
	
}
