package com.aiz.gulimall.member.dao;

import com.aiz.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 23:00:05
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
