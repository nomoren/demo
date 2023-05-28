package com.ex.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhibin
 * @date 2023/5/27
 */
@Component
public class ProducerDemo {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String msg){
        // 发送消息到test主题 0分区 key为key
        kafkaTemplate.send("test",0,"key",msg);
    }

}
