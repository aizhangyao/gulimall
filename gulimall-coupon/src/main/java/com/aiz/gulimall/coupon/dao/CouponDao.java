package com.aiz.gulimall.coupon.dao;

import com.aiz.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 23:07:47
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
