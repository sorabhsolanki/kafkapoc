package com.example.kafka.poc.kafkapoc.cmdrunner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestCommandLineRunner implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(TestCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Testing of cmd runner.");
    }

}
