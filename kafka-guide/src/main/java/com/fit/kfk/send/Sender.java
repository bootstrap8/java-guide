package com.fit.kfk.send;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-08-28
 */
@Slf4j
public class Sender {

    private KafkaProducer<String, String> producer;

    public final static String TOPIC = "Test_Msg_Topic";

    public Sender() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.56.4:9092");
        props.put("acks", "1");
        //props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    /**
     *
     */
    public void send(int cnt) {
        for (int i = 0; i < cnt; i++) {
            String key = "00|" + i;
            String data = String.format("send msg %s to kfk", i + 300);
            try {
                producer.send(new ProducerRecord<>(TOPIC, key, data), (RecordMetadata metadata, Exception e) -> {
                    if (e != null) {
                        log.error("", e);
                    } else {
                        log.info("send ok");
                    }
                });
                log.info("send to {} msg : {}", TOPIC, data);
            } catch (Exception e) {
                log.error("send kfk msg error:", e);
            }
        }
        producer.flush();
    }

    /**
     *
     */
    public void destroy() {
        if (producer != null) {
            producer.close();
        }
    }

    public static void main(String[] args) {
        Sender sender = new Sender();
        sender.send(3);
        sender.destroy();
    }

}
