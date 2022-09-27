package com.aiz.gulimall.order.dao;

import com.aiz.gulimall.order.entity.UndoLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 22:02:49
 */
@Mapper
public interface UndoLogDao extends BaseMapper<UndoLogEntity> {
	
}
