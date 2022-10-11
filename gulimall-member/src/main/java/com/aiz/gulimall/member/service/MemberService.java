package com.aiz.gulimall.member.service;

import com.aiz.gulimall.member.exception.PhoneExistException;
import com.aiz.gulimall.member.exception.UserNameExistException;
import com.aiz.gulimall.member.vo.MemberUserLoginVo;
import com.aiz.gulimall.member.vo.MemberUserRegisterVo;
import com.aiz.gulimall.member.vo.SocialUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aiz.common.utils.PageUtils;
import com.aiz.gulimall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author ZhangYao
 * @email zhangyao_xz@foxmail.com
 * @date 2022-09-27 23:00:05
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 用户注册
     */
    void register(MemberUserRegisterVo vo);

    /**
     * 判断邮箱是否重复
     */
    void checkPhoneUnique(String phone) throws PhoneExistException;

    /**
     * 判断用户名是否重复
     */
    void checkUserNameUnique(String userName) throws UserNameExistException;

    /**
     * 用户登录
     */
    MemberEntity login(MemberUserLoginVo vo);

    /**
     * 社交用户的登录
     */
    MemberEntity login(SocialUser socialUser) throws Exception;
}

