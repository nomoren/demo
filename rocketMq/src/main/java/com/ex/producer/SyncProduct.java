package com.ex.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author amos
 * @date 2023/7/6
 */
public class SyncProduct {

    public static void main(String[] args) throws Exception{
    }

    public static void sync() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("test");
        producer.setNamesrvAddr("xxx:9876");
        producer.start();
        Message message = new Message("test", "tag","hello".getBytes());
        // 同步发送
        SendResult send = producer.send(message);
        // 单向发送
        ///producer.sendOneway(message);
    }

    public static void async() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("test");
        producer.setNamesrvAddr("xxx:9876");
        // 设置异步发送失败重试次数
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 设置默认队列数
        producer.setDefaultTopicQueueNums(1);
        producer.start();

        Message message = new Message("test", "tag","hello".getBytes());
        producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        // o为arg参数
                        return null;
                    }
                }, 1, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {

                    }
                    @Override
                    public void onException(Throwable throwable) {

                    }
                });
    }
}
