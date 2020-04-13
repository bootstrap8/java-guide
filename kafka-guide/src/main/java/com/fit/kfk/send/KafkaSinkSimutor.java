package com.fit.kfk.send;

import com.fit.util.JsonUtil;
import com.fit.util.NodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.Properties;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-12-20
 */
@Slf4j
public class KafkaSinkSimutor {

    private KafkaProducer<String, String> producer;

    public void readyGo() {
        //kafka 生产者
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.21.17.228:9092");
        props.put("acks", "1");
        props.put("retries", "3");
        props.put("batch.size", "16384");
        props.put("linger.ms", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
        log.info("初始化Kafka生产者完成");
    }


    public void sendMsg(String topic, Map msg) {
        String json = JsonUtil.toJson(msg);
        producer.send(new ProducerRecord<>(topic, json), (RecordMetadata metadata, Exception ex) -> {
            if (ex != null) {
                log.error("send <1> msg to {} onComplete error:", topic, ex);
            } else {
                log.info("kafka sink {} ok, msg : {}", topic, json);
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        KafkaSinkSimutor sink = new KafkaSinkSimutor();
        String topic = "AlarmStat-Alarm-Abims-AlarmStat-NeChange-Active";
        Map node = NodeUtil.createElement();
        Map subNode = NodeUtil.createElement();
        NodeUtil.addAttr(subNode, "alarmstatus", "0");
        NodeUtil.addAttr(subNode, "alarmuniqueid", "00|5");
        //NodeUtil.addAttr(subNode, "neid", "bts.ne.001");
        NodeUtil.addAttr(subNode, "alarmseverity", "2");
        NodeUtil.addAttr(subNode, "alarmtype", "0");
        NodeUtil.addAttr(subNode, "daltime", String.valueOf(System.currentTimeMillis() / 1000L));
        NodeUtil.addSubNode(node, subNode);
        sink.readyGo();
        sink.sendMsg(topic, node);
        Thread.sleep(1000);
        System.out.println("send ok");
    }


}
