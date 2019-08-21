package com.example.kafka.poc.kafkapoc.util;

import org.aeonbits.owner.ConfigFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class PublisherConsumerProperties {

    private static final Logger LOG = LoggerFactory.getLogger(PublisherConsumerProperties.class);

    private final KafkaProperties cfg = ConfigFactory.create(KafkaProperties.class);
    private final Properties configPublisherProperties = new Properties();
    private final Properties configConsumerProperties = new Properties();

    @PostConstruct
    public void init(){
        initPublisherProperties();
        initConsumerProperties();
    }

    private void initConsumerProperties() {
        LOG.info("Initializing consumer properties !");
        configConsumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, cfg.bootstrapServer());
        configConsumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configConsumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configConsumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, cfg.groupId());
        configConsumerProperties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, cfg.maxPollRecords());
    }

    private void initPublisherProperties() {
        LOG.info("Initializing publisher properties !");

        configPublisherProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, cfg.bootstrapServer());
        configPublisherProperties.put(ProducerConfig.ACKS_CONFIG, cfg.acks());
        configPublisherProperties.put(ProducerConfig.RETRIES_CONFIG, cfg.retries());
        configPublisherProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, cfg.batchSize());
        configPublisherProperties.put(ProducerConfig.LINGER_MS_CONFIG, cfg.lingerMs());
        configPublisherProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, cfg.bufferMemory());
        configPublisherProperties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, cfg.blockTime());

        configPublisherProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configPublisherProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    public Properties getConfigConsumerProperties() {
        return configConsumerProperties;
    }

    public Properties getConfigPublisherProperties() {
        return configPublisherProperties;
    }
}
