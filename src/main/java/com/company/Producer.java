package com.company;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class Producer {
        public void execMsgSend() throws  Exception{
            //kafka configuration
            Properties props = new Properties();
            props.put("bootstrap.servers", "127.0.0.1:9092");
            props.put("acks", "1");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);


            String topic = "rachelpoc-collectlog";
            for (int i = 10; i <= 20; i++) {
                String value = " this is another message_" + i;
                ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,i+"",value);
                kafkaProducer.send(record,new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
                    }
                });
                System.out.println(i+" ----   success");
                Thread.sleep(1000);
            }
            System.out.println("send message over.");
            kafkaProducer.close();
        }



}
