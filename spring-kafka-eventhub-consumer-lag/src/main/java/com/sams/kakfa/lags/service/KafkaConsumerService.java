package com.sams.kakfa.lags.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumerService {


	@KafkaListener(topics = "<topic_name>", groupId = "APM0008759-Consumer-1", containerFactory = "kafkaListenerContainerFactory")
	public void processMessage(String payload, MessageHeaders messageHeaders) {
		log.info("<<KafkaConsumerService>> {} ", messageHeaders);
		String messageId = String.valueOf(messageHeaders.get("MESSAGE-ID"));
		log.info("Message Id received from Router {} ", messageId);
		log.info("<<KafkaConsumerService>> {} ", payload);
	}

}