package com.sams.kakfa.lags.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@PropertySource("classpath:application.yaml")
public class EventHubConsumerService {
	
	@Autowired
	KafkaProducerService kafkaProducerService;
	
	private String topicName ="APM0008759-servicing-layer-PROD-message-transformer";
	
	@KafkaListener(topics = "#{'${spring.kafka.topics}'.split(',')}" ,
			 groupId = "test-consumer",
			containerFactory = "eventHubListenerContainerFactory")
	public void consume(String riversandExportedJson, Acknowledgment ack) {
			log.info("<<EventHubConsumerService>> {} ",riversandExportedJson);
			kafkaProducerService.sendMessage(UUID.randomUUID().toString(),riversandExportedJson, topicName);
			ack.acknowledge();
    }

}
