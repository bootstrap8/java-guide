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

    public Sender() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "106.54.140.164:9092");
//        props.put("bootstrap.servers", "192.168.56.4:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        List<String> interceptors = new ArrayList<>();
//        interceptors.add("huxi.test.producer.TimeStampPrependerInterceptor"); // interceptor 1
//        interceptors.add("huxi.test.producer.CounterInterceptor"); // interceptor 2
//        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
        producer = new KafkaProducer<>(props);
    }

    /**
     *
     */
    public void send(String topic, int cnt) {
        for (int i = 1; i <= cnt; i++) {
            String key = topic + "|" + i;
            String data = key;
            try {
                producer.send(new ProducerRecord<>(topic, key, data), (RecordMetadata metadata, Exception e) -> {
                    if (e != null) {
                        log.error("", e);
                    } else {
                        //log.info("send ok");
                    }
                });
                log.info("send to {} msg : {}", topic, data);
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
        sender.send("foo", 100);
        sender.send("bar", 100);
        sender.destroy();
    }

}
