package com.fit.kfk.send;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-08-28
 */
@Slf4j
public class Sender {

    private KafkaProducer<String, String> producer;

    public final static String TOPIC = "AlarmTopicTestZookeeper";

    public Sender() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.56.4:9092");
        props.put("acks", "1");
        //props.put("acks", "all");//所有follower都响应了才认为消息提交成功，即"committed"
        props.put("retries", 0);//retries = MAX 无限重试，直到你意识到出现了问题:)
        props.put("batch.size", 16384);//producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        //batch.size当批量的数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        props.put("linger.ms", 1);//延迟1ms发送，这项设置将通过增加小的延迟来完成--即，不是立即发送一条记录，producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        props.put("buffer.memory", 33554432);//producer可以用来缓存数据的内存大小。
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    /**
     *
     */
    public void send() {

        int count = 10000;
        for (int i = 0; i < count; i++) {
            String key = "00|" + (i + 300);
            String data = String.format("send msg %s to kfk", i + 300);
            try {
                Future<RecordMetadata> future = producer.send(new ProducerRecord<>(TOPIC, key, data));
                log.info("send to {} msg : {}", TOPIC, data);
                producer.flush();
                //log.info("-->{}", future.get());
            } catch (Exception e) {
                log.error("send kfk msg error:", e);
            }
        }
    }

    /**
     *
     */
    public void destory() {
        if (producer != null) {
            producer.close();
        }
    }

    public static void main(String[] args) {
        Sender sender = new Sender();
        sender.send();
    }

}
