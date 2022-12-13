package com.aiz.gulimall.order.dao;

import com.aiz.gulimall.order.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName OrderDaoTest
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 16:35 2022/11/3
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;

    @Test
    void getOrderDetail() {
        List<OrderEntity> orderDetail = orderDao.getOrderDetail();
        for (OrderEntity orderEntity : orderDetail) {
            System.out.println(orderEntity.getId() + " - " +orderEntity.getOrderSn());
        }
    }
}