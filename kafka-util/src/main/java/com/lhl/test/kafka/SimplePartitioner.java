package com.lhl.test.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liuhonglin on 2017/11/3.
 */
public class SimplePartitioner implements Partitioner {

    @Override
    public int partition(String topic,
                         Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes,
                         Cluster cluster) {

        int partition = 0;

        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        String strKey = (String) key;

        try {
            partition = Math.abs(Integer.parseInt(strKey) % numPartitions);
        } catch (NumberFormatException e) {
            partition = Math.abs(strKey.hashCode() % numPartitions);
        }

        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
