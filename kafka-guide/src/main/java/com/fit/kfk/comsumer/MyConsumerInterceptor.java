package com.fit.kfk.comsumer;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Map;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-11-05
 */
public class MyConsumerInterceptor implements ConsumerInterceptor {

    @Override
    public ConsumerRecords onConsume(ConsumerRecords records) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void onCommit(Map offsets) {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
