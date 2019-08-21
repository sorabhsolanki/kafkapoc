package com.example.kafka.poc.kafkapoc.util;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadOnStartup {

    private final PublisherConsumerProperties publisherConsumerProperties;

    @Autowired
    public LoadOnStartup(PublisherConsumerProperties publisherConsumerProperties) {
        this.publisherConsumerProperties = publisherConsumerProperties;
    }

    @Bean(name = "sampleKafkaProducer")
    public KafkaProducer<String, String> sampleKafkaProducer(){
        return new KafkaProducer<String, String>(publisherConsumerProperties.getConfigPublisherProperties());

    }

    @Bean(name = "sampleKafkaConsumer")
    public KafkaConsumer<String, String> sampleKafkaConsumer(){
        return new KafkaConsumer<String, String>(publisherConsumerProperties.getConfigConsumerProperties());
    }
}
