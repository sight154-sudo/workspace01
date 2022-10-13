package com.huawei.kafka;
 
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
 
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
 
/**
 * 消费者代码
 * 1、自动提交offset
 */
 
public class OrderConsumerDemo1 {
    public static void main(String[] args) {
 
        // 1、连接集群
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.31.132:9092");
        props.put("group.id", "heima129");
 
        //以下两行代码 ---消费者自动提交offset值
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms",  "1000");
 
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
 
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
 
        //		 2、发送数据 发送数据需要，订阅下要消费的topic   order
        kafkaConsumer.subscribe(Arrays.asList("heima129"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                int partition = record.partition();  // 获取数据对应的分区号
                String value = record.value(); // 对应数据值
                long lastoffset = record.offset(); //对应数据的偏移量
                String key = record.key();//对应数据发送的key
 
                System.out.println("数据的key为 "+ key + " 数据的value为 " + value + " 数据的offset为 "+ lastoffset + " 数据的分区为 "+ partition);
            }
        }
    }
}