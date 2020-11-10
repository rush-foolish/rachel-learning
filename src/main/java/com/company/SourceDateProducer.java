package com.company;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SourceDateProducer {


    private static String[] messageTemp;
    private static String[] status = {"start", "waiting", "running", "success"};
    private static String[] jobName = {"CAD_BAL", "CAD_IND", "DEB_BAL", "DEB_IND", "EAST", "OPTIMA"};
    private static String[] component = {"common-file-watcher", "staging", "data-enrichment", "data-unloading", "rule-parser", "rdm-loader"};
    private static String[] asOfDate = {"2020-07-31", "2020-08-01", "2020-08-02", "2020-08-03", "2020-08-04", "2020-08-05"};

    private static KafkaProducer<String, String> kafkaProducer;


    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<String, String>(props);

        String CMFMessageTemp = "%s,%s,%s";
        String stgMessageTemp = "%s, %s %s";
        String dtsMessageTemp = "%s, %s %s";
        String unloadMessageTemp = "%s, %s %s";

        messageTemp = new String[]{CMFMessageTemp,stgMessageTemp,dtsMessageTemp,unloadMessageTemp};
    }

    public static void main(String[] args) {


        for (int i = 0; i < asOfDate.length; i++) {

            workFlowLog(i);
        }


    }

    public static void workFlowLog(int asOfDateIndex) {
        for (int i = 0; i < component.length; i++) {

            oneStepLog(asOfDateIndex,i);

        }
    }


    public static void oneStepLog(int asOfDateIndex,int stepIndex) {

        try {

            for (int i = 0; i < status.length; i++) {
                Thread.sleep(1000 * 30);
                sendFormatMessage(asOfDate[asOfDateIndex], stepIndex,status[i]);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void sendFormatMessage(String asOfDate, int stepIndex, String status) {

            for (int i=0; i< jobName.length; i++){
                final String message = String.format(messageTemp[stepIndex], status,asOfDate,jobName[i]);
                ProducerRecord record = new ProducerRecord<String, String>(component[stepIndex], message);
                kafkaProducer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        System.out.println("message send to partition " + metadata.partition()
                                + ", offset: " + metadata.offset() + "  " + message);
                    }
                });
            }





    }


}
