package com.aiz.gulimall.order.service.impl;

import com.aiz.common.utils.R;
import com.aiz.common.vo.MemberResponseVo;
import com.aiz.gulimall.order.feign.CartFeignService;
import com.aiz.gulimall.order.feign.MemberFeignService;
import com.aiz.gulimall.order.feign.WmsFeignService;
import com.aiz.gulimall.order.interceptor.LoginUserInterceptor;
import com.aiz.gulimall.order.vo.MemberAddressVo;
import com.aiz.gulimall.order.vo.OrderConfirmVo;
import com.aiz.gulimall.order.vo.OrderItemVo;
import com.aiz.gulimall.order.vo.SkuStockVo;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiz.common.utils.PageUtils;
import com.aiz.common.utils.Query;

import com.aiz.gulimall.order.dao.OrderDao;
import com.aiz.gulimall.order.entity.OrderEntity;
import com.aiz.gulimall.order.service.OrderService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static com.aiz.gulimall.order.constant.OrderConstant.USER_ORDER_TOKEN_PREFIX;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private CartFeignService cartFeignService;

    @Autowired
    private WmsFeignService wmsFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        MemberResponseVo memberResponseVo = LoginUserInterceptor.loginUser.get();
        //1、远程查询所有的收获地址列表
        List<MemberAddressVo> address = memberFeignService.getAddress(memberResponseVo.getId());
        confirmVo.setMemberAddressVos(address);
        //2、远程查询购物车所有选中的购物项
        List<OrderItemVo> currentCartItems = cartFeignService.getCurrentCartItems();
        confirmVo.setItems(currentCartItems);

        List<OrderItemVo> items = confirmVo.getItems();
        //获取全部商品的id
        List<Long> skuIds = items.stream()
                .map((itemVo -> itemVo.getSkuId()))
                .collect(Collectors.toList());
        //远程查询商品库存信息
        R skuHasStock = wmsFeignService.getSkuHasStock(skuIds);
        List<SkuStockVo> skuStockVos = (List<SkuStockVo>) skuHasStock.getData("data", new TypeReference<List<SkuStockVo>>() {
        });
        if (skuStockVos != null && skuStockVos.size() > 0) {
            //将skuStockVos集合转换为map
            Map<Long, Boolean> skuHasStockMap = skuStockVos.stream().collect(Collectors.toMap(SkuStockVo::getSkuId, SkuStockVo::getHasStock));
            confirmVo.setStocks(skuHasStockMap);
        }
        //4、价格数据自动计算

        //TODO 5、防重令牌(防止表单重复提交)

        return confirmVo;
    }

}