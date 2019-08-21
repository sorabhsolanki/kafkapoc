package com.example.kafka.poc.kafkapoc.producer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("loadOnStartup")
public class RecordProducer {

    private final static Logger LOG = LoggerFactory.getLogger(RecordProducer.class);

    private final KafkaProducer<String, String> sampleKafkaProducer;

    @Autowired
    public RecordProducer(@Qualifier("sampleKafkaProducer") KafkaProducer<String, String> sampleKafkaProducer) {
        this.sampleKafkaProducer = sampleKafkaProducer;
    }

    public void sendRecords(){
        LOG.info("Trying to start producing record.");
        for(int i = 0; i < 10; i ++){

            ProducerRecord<String, String> record = new ProducerRecord<>("test", "hello-world-" + i);
            sampleKafkaProducer.send(record, (recordMetadata, e) -> {
                if(e == null){
                   LOG.info("Received new metadata : Topic : {}, Partition {}, Offset {}", recordMetadata.topic(),
                           recordMetadata.partition(), recordMetadata.offset());
                }else {
                    LOG.error("Error while producing record : {}", e);
                }
            });

        }

    }


}
