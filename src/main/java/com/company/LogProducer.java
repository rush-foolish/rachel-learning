package com.company;

import com.company.logentity.LogEntity;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.LinkedHashMap;
import java.util.Properties;

public class LogProducer {

    private KafkaProducer<String, String> kafkaProducer;
    private LinkedHashMap<String, String> topicMap;

    private LogProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<String, String>(props);
        topicMap = new LinkedHashMap<String, String>();
        topicMap.put("FILEWATCHER", "common-file-watcher");
        topicMap.put("FILESTAGING", "file-staging");
        topicMap.put("DATAENRICHMENT",  "data-enrichment");
        topicMap.put("RULEPARSER", "rule-parser");
        topicMap.put("RDMLOADER", "rdm-loader");

    }

    private static class LogProducerHolder {
        private static final LogProducer INSTANCE = new LogProducer();
    }

    public static final LogProducer getInstance() {
        return LogProducerHolder.INSTANCE;
    }

    public void sendMessage(LogEntity logEntity) {
        String topic = topicMap.get(logEntity.getComponent().name()).toString();
        final String sendMsg = logEntity.toJson();
        ProducerRecord record = new ProducerRecord<String, String>(topic, sendMsg);
        kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println("message send to partition " + metadata.partition()
                        + ", offset: " + metadata.offset() + "  " + sendMsg);
            }
        });
    }


}
