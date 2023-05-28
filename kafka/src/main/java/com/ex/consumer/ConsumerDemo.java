package com.ex.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhibin
 * @date 2023/5/27
 */
@Component
public class ConsumerDemo {

    //主题和分区
    // 消费test主题的0 1分区
    // 消费test2主题的2分区 从100开始消费
    @KafkaListener(topics = "test",groupId = "test-consumer",topicPartitions = {
            @TopicPartition(topic = "test",partitions = {"0","1"}),
            @TopicPartition(topic = "test2",partitions = {"2"},partitionOffsets = @PartitionOffset(partition = "2",initialOffset = "100"))
    })
    public void receive(ConsumerRecord<String,String> ms, Acknowledgment ack){
        System.out.println(ms.value());
        System.out.println(ms.key());
        // 提交ack
        ack.acknowledge();
    }
}
