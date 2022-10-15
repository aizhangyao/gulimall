package com.aiz.gulimall.order.controller;

import com.aiz.gulimall.order.entity.OrderEntity;
import com.aiz.gulimall.order.entity.OrderReturnReasonEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName RabbitController
 * @Description TODO
 * @Author ZhangYao
 * @Date Create in 23:01 2022/10/15
 * @Version 1.0
 */

@Slf4j
@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMq")
    public String sendMq(@RequestParam(value = "num", defaultValue = "10") Integer num) {

        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) {
                OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
                reasonEntity.setId(1L);
                reasonEntity.setCreateTime(new Date());
                reasonEntity.setName("ZhangYao-" + i);
                reasonEntity.setStatus(1);
                reasonEntity.setSort(2);
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java",
                        reasonEntity, new CorrelationData(UUID.randomUUID().toString()));
                log.info("消息发送完成:{}", reasonEntity);
            } else {
                OrderEntity orderEntity = new OrderEntity();
                orderEntity.setOrderSn(UUID.randomUUID().toString());
                orderEntity.setReceiverName("ZhangYao-" + i);
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello22.java",
                        orderEntity, new CorrelationData(UUID.randomUUID().toString()));
            }
        }
        return "ok";
    }
}