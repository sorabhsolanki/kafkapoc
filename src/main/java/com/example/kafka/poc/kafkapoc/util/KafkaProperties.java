package com.example.kafka.poc.kafkapoc.util;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "http://localhost/kafka.properties" })
public interface KafkaProperties extends Config{

    public String bootstrapServer();
    public String acks();
    public String retries();
    public int batchSize();
    public int lingerMs();
    public int bufferMemory();
    public int blockTime();
    public String keySerializer();
    public String valueSerializer();
    public String groupId();
    public String maxPollRecords();
}
