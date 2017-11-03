package com.lhl.test.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Created by liuhonglin on 2017/11/1.
 */
public class Sender {

    public static void main(String[] args) {

        String topic = "test2";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.127.92.182:9092,10.127.92.182:9093,10.127.92.182:9094");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        /**
         * 这个参数用于通知broker接收到message后是否向producer发送确认信号
         *  0:  表示producer不用等待任何确认信号，会一直发送消息，
         * 否则producer进入等待状态
         * -1/all: 表示leader状态的replica需要等待所有in-sync状态的replica都接收到消息后才会向producer发送确认信号，
         * 再次之前producer一直处于等待状态
         * "request.required.acks"
         */
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "1");
        //配置partitionner选择策略
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, SimplePartitioner.class.getName());

        Producer<String, String> producer = new KafkaProducer(properties);

        producer.send(new ProducerRecord<String, String>(topic, "testkey", "myMessage"),
                new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            exception.printStackTrace();
                        } else {
                            System.out.println("The offset of the record we just sent is: " + metadata.offset());
                        }
                    }
                });

        producer.close();

    }
}
