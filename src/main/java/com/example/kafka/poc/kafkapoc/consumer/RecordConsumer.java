package com.example.kafka.poc.kafkapoc.consumer;


import com.example.kafka.poc.kafkapoc.producer.RecordProducer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
@DependsOn("loadOnStartup")
public class RecordConsumer {

    private final static Logger LOG = LoggerFactory.getLogger(RecordProducer.class);

    private final KafkaConsumer<String, String> sampleKafkaConsumer;

    @Autowired
    public RecordConsumer(@Qualifier("sampleKafkaConsumer") KafkaConsumer<String, String> sampleKafkaConsumer) {
        this.sampleKafkaConsumer = sampleKafkaConsumer;
    }


    public void consumerRecords(){
        //sampleKafkaConsumer.subscribe(Collections.singletonList("test"));
        TopicPartition partition = new TopicPartition("test", 0);
        sampleKafkaConsumer.assign(Arrays.asList(partition));
        sampleKafkaConsumer.seek(partition, 24);

        while (true){
            ConsumerRecords<String, String> records = sampleKafkaConsumer.poll(10000);
            if (records.count() > 0) {
                LOG.info("Consuming topic {} from Kafka with batch size {}", "test", records.count());
                records.forEach(record -> {
                    LOG.info("Key : {}, Value : {}", record.key(), record.value());
                    LOG.info("Partition: {}, Offset: {}", record.partition(), record.offset());
                });
            }
            saveConsumerConfig(sampleKafkaConsumer, partition);
            sampleKafkaConsumer.commitSync();
        }



    }

    private void saveConsumerConfig(KafkaConsumer<String, String> sampleKafkaConsumer, TopicPartition partition) {
        LOG.info("Current offset is {}", sampleKafkaConsumer.position(partition));
    }
}
