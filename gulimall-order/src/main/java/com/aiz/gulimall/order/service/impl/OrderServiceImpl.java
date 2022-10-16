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

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

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
        //构建OrderConfirmVo
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        //获取当前用户登录的信息
        MemberResponseVo memberResponseVo = LoginUserInterceptor.loginUser.get();
        //TODO :获取当前线程请求头信息(解决Feign异步调用丢失请求头问题)
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //开启第一个异步任务
        CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            //1、远程查询所有的收获地址列表
            List<MemberAddressVo> address = memberFeignService.getAddress(memberResponseVo.getId());
            confirmVo.setMemberAddressVos(address);
        }, threadPoolExecutor);

        //开启第二个异步任务
        CompletableFuture<Void> cartInfoFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            //2、远程查询购物车所有选中的购物项
            List<OrderItemVo> currentCartItems = cartFeignService.getCurrentCartItems();
            confirmVo.setItems(currentCartItems);
            //feign在远程调用之前要构造请求，调用很多的拦截器

        }, threadPoolExecutor).thenRunAsync(() -> {
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
        }, threadPoolExecutor);

        //3、查询用户积分
        Integer integration = memberResponseVo.getIntegration();
        confirmVo.setIntegration(integration);

        //4、价格数据自动计算

        //TODO 5、防重令牌(防止表单重复提交)
        //为用户设置一个token，三十分钟过期时间（存在redis）
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(USER_ORDER_TOKEN_PREFIX + memberResponseVo.getId(), token, 30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);

        CompletableFuture.allOf(addressFuture, cartInfoFuture).get();

        return confirmVo;
    }

}