package com.aiz.gulimall.order.service.impl;

import com.aiz.gulimall.order.entity.OrderEntity;
import com.aiz.gulimall.order.entity.OrderReturnReasonEntity;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiz.common.utils.PageUtils;
import com.aiz.common.utils.Query;

import com.aiz.gulimall.order.dao.MqMessageDao;
import com.aiz.gulimall.order.entity.MqMessageEntity;
import com.aiz.gulimall.order.service.MqMessageService;

@RabbitListener(queues = {"hello-java-queue"})
@Slf4j
@Service("mqMessageService")
public class MqMessageServiceImpl extends ServiceImpl<MqMessageDao, MqMessageEntity> implements MqMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MqMessageEntity> page = this.page(
                new Query<MqMessageEntity>().getPage(params),
                new QueryWrapper<MqMessageEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * queues：声明需要监听的队列
     *
     * org.springframework.amqp.core.Message
     *
     * 参数可以写一下类型
     * 1、Message message：原生消息详细信息。头 + 体
     * 2、T<发送消息的类型> OrderReturnReasonEntity content
     * 3、Channel channel：当前传输数据的通道
     *
     * Queue：队列可以很多人监听。只要收到消息，队列删除消息，而且只能有一个收到此消息
     * 场景：
     *  1)订单服务启动多个，同一个消息，只能有一个客户端收到
     *  2)只有一个消息完全处理完，方法运行结束，我们才能接受到下一个消息
     */
//    @RabbitListener(queues = {"hello-java-queue"})
    @RabbitHandler
    public void receiveMessage(Message message,
                               OrderReturnReasonEntity content, Channel channel) throws InterruptedException {
        log.info("接受到的消息...内容" + message + "===内容：" + content);
        //Thread.sleep(3000);
        //拿到主体内容
        byte[] body = message.getBody();
        //拿到的消息头属性信息
        MessageProperties messageProperties = message.getMessageProperties();
        //channel内按序号自增的
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        //签收货物，非批量模式
        try {
            if(deliveryTag%2==0){
                //收货
                channel.basicAck(deliveryTag,false);
                log.info("签收了货物" + deliveryTag);
            }else {
                //退货 第三个参数 false丢弃 true发回服务器
                channel.basicNack(deliveryTag,false,false);
                log.info("退货了货物" + deliveryTag);
            }

        } catch (IOException e) {
            // 网络中断
            e.printStackTrace();
        }

        log.info("消息处理完成=>" + content.getName());
    }

    @RabbitHandler
    public void receiveMessage2(Message message,
                                OrderEntity content) {
        log.info("接受到的消息...内容" + message + "===内容：" + content);
    }

}