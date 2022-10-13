package com.huawei.kafka;
 
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
 
import java.util.List;
import java.util.Map;
import java.util.Random;
 
/*
 * kafka当中的自定义数据分区
 */
 
public class KafkaCustomPartitionerDemo1 implements Partitioner {
    @Override
    public int partition(String topic, Object arg1, byte[] keyBytes, Object arg3, byte[] arg4, Cluster cluster) {
 
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int partitionNum = partitions.size();
        Random random = new Random();
        int partition = random.nextInt(partitionNum);
 
        return partition;
    }
 
    @Override
    public void close() {
 
    }
 
    @Override
    public void configure(Map<String, ?> map) {
 
    }
}