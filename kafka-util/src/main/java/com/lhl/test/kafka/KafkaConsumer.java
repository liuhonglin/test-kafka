package com.lhl.test.kafka;

import kafka.serializer.StringDecoder;
import kafka.tools.ConsoleConsumer;
import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liuhonglin on 2017/11/2.
 */
public class KafkaConsumer {

    public static Consumer getConsumerConnector() {
        Properties properties = new Properties();

        /**
        properties.setProperty("zookeeper.connect", "10.127.92.182:2181,10.127.92.182:2182,10.127.92.182:2183");
        properties.setProperty("group.id", "testgroup");
        properties.setProperty("zookeeper.session.timeout.ms", "4000");
        properties.setProperty("zookeeper.sync.time.ms", "200");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("auto.offset.reset", "smallest");
        properties.setProperty("serializer.class", "kafka.serializer.StringEncoder");
        */

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.127.92.182:9092,10.127.92.182:9093,10.127.92.182:9094");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "testgroup2");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); //latest/earliest
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        return new org.apache.kafka.clients.consumer.KafkaConsumer(properties);
    }

    public static void main(String[] args) {

        Consumer consumer = getConsumerConnector();
        consumer.subscribe(Arrays.asList("test2"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
