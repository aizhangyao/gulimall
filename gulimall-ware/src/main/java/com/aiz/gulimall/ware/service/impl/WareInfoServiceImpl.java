package com.aiz.gulimall.ware.service.impl;

import com.aiz.common.utils.R;
import com.aiz.gulimall.ware.feign.MemberFeignService;
import com.aiz.gulimall.ware.vo.FareVo;
import com.aiz.gulimall.ware.vo.MemberAddressVo;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiz.common.utils.PageUtils;
import com.aiz.common.utils.Query;

import com.aiz.gulimall.ware.dao.WareInfoDao;
import com.aiz.gulimall.ware.entity.WareInfoEntity;
import com.aiz.gulimall.ware.service.WareInfoService;
import org.springframework.util.StringUtils;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> wareInfoEntityQueryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wareInfoEntityQueryWrapper.eq("id",key).or()
                    .like("name",key)
                    .or().like("address",key)
                    .or().like("areacode",key);
        }

        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                wareInfoEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    /**
     * 计算运费
     */
    @Override
    public FareVo getFare(Long addrId) {
        FareVo fareVo = new FareVo();
        //收获地址的详细信息
        R addrInfo = memberFeignService.info(addrId);
        MemberAddressVo memberAddressVo = (MemberAddressVo) addrInfo.getData("memberReceiveAddress", new TypeReference<MemberAddressVo>() {
        });
        if (memberAddressVo != null) {
            String phone = memberAddressVo.getPhone();
            //截取用户手机号码最后一位作为我们的运费计算
            //178xxxx3916
            String fare = phone.substring(phone.length() - 10, phone.length() - 8);
            BigDecimal bigDecimal = new BigDecimal(fare);

            fareVo.setFare(bigDecimal);
            fareVo.setAddress(memberAddressVo);

            return fareVo;
        }
        return null;
    }
}