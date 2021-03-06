package com.fit.kfk.comsumer;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-25
 */
@Slf4j
public class AssignConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        //properties.put("bootstrap.servers", "192.168.56.4:9092");
        properties.put("bootstrap.servers", "106.54.140.164:9092");
        properties.put("group.id", "stone-group-1001");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        //properties.put("auto.offset.reset", "latest");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);

        String fooTopic = "foo";
        String barTopic = "bar";
        kafkaConsumer.subscribe(Arrays.asList(fooTopic, barTopic));

        kafkaConsumer.poll(100);

        TopicPartition fooTopicPartition = new TopicPartition(fooTopic, 0);
        kafkaConsumer.seek(fooTopicPartition, 30);
        TopicPartition barTopicPartition = new TopicPartition(barTopic, 0);
        kafkaConsumer.seek(barTopicPartition, 10);

        boolean running = true;
        int count = 0;

        Map<String, Integer> countMap = Maps.newHashMap();
        while (running) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String topic = record.topic();
                if (countMap.get(topic) == null) {
                    countMap.put(topic, 1);
                } else {
                    countMap.put(topic, countMap.get(topic) + 1);
                }
//                log.info("Topic : {} offset = {}, value = {}", topic, record.offset(), record.value());
                if (countMap.get(topic) < 10) {
                    System.out.println(record.offset() + "-" + record.value());
                }
                count++;
            }
        }
    }

}
