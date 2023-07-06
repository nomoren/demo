package com.ex.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author amos
 * @date 2023/7/6
 */
public class PushConsumer {


    public static void consumer() throws Exception{
        // 创建消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test");
        // 设置nameserver地址
        consumer.setNamesrvAddr("");
        // 设置消费者订阅的主题
        consumer.subscribe("test","*");
        // 设置消费者消费消息的起始位置
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 设置消费者消费消息的模式,默认是集群模式
        //consumer.setMessageModel(MessageModel.BROADCASTING);
        // 设置消费者消费消息的回调函数
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者
        consumer.start();
    }

}
