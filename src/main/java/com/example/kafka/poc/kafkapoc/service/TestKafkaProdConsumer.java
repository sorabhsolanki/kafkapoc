package com.example.kafka.poc.kafkapoc.service;

import com.example.kafka.poc.kafkapoc.consumer.RecordConsumer;
import com.example.kafka.poc.kafkapoc.producer.RecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestKafkaProdConsumer {

    private final static Logger LOG = LoggerFactory.getLogger(TestKafkaProdConsumer.class);

    private final RecordProducer recordProducer;
    private final RecordConsumer recordConsumer;

    @Autowired
    public TestKafkaProdConsumer(RecordProducer recordProducer, RecordConsumer recordConsumer) {
        this.recordProducer = recordProducer;
        this.recordConsumer = recordConsumer;
    }

    @PostConstruct
    public void test(){
        try {
            new Thread(() -> {
                LOG.info("Starting Consumer:");
                recordConsumer.consumerRecords();
            }).start();

            LOG.info("Sleeping for 10 sec.");
            Thread.sleep(10000);
            LOG.info("Starting Producer:");
            recordProducer.sendRecords();
        }catch (Exception ex){
            LOG.error(ex.getMessage());
        }
    }

}
