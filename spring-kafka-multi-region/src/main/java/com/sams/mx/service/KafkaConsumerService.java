package com.sams.mx.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

	@KafkaListener(topics = "#{'mx-rs-message-transformer'.split(',')}", groupId = "mx-rs-message-transformer-consumer", containerFactory = "kafkaListenerContainerFactoryDC1", autoStartup = "true")
	public void consumeEventsFromKafkaDC1(String riversandJson, Acknowledgment ack, MessageHeaders messageHeaders) {
		log.info("<<consumeEventsFromKafkaDC1>>  The Event Body Received From Kafka is {}", riversandJson);
		try {
			String messageId = String.valueOf(messageHeaders.get("MESSAGE-ID"));
			log.info("<<consumeEventsFromKafkaDC1>> Message Id received  {} ", messageId);
		} catch (Exception ex) {
			log.error("<<KafkaConsumerService>> <<consumeEventsFromKafkaDC1>> Error Occured{} ", ex.getMessage());

		}
		ack.acknowledge();

	}

	@KafkaListener(topics = "#{'kafka-v2-mx-secure-az-stg-wus.mx-rs-message-transformer'.split(',')}", groupId = "mx-rs-message-transformer-consumer", containerFactory = "kafkaListenerContainerFactoryDC1", autoStartup = "false")
	public void consumeEventsFromKafkaDC1Replica(String riversandJson, Acknowledgment ack,
			MessageHeaders messageHeaders) {
		log.info("<<consumeEventsFromKafkaDC1Replica>>  The Event Body Received From Kafka is {}", riversandJson);
		try {
			String messageId = String.valueOf(messageHeaders.get("MESSAGE-ID"));
			log.info("<<consumeEventsFromKafkaDC1Replica>> Message Id received  {} ", messageId);
		} catch (Exception ex) {
			log.error("<<KafkaConsumerService>> <<consumeEventsFromKafkaDC1Replica>> Error Occured{} ",
					ex.getMessage());

		}
		ack.acknowledge();

	}

	@KafkaListener(topics = "#{'mx-rs-message-transformer'.split(',')}", groupId = "mx-rs-message-transformer-consumer", containerFactory = "kafkaListenerContainerFactoryDC2", autoStartup = "true")
	public void consumeEventsFromKafkaDC2(String riversandJson, Acknowledgment ack, MessageHeaders messageHeaders) {
		log.info("<<consumeEventsFromKafkaDC2>>  The Event Body Received From Kafka is {}", riversandJson);
		try {
			String messageId = String.valueOf(messageHeaders.get("MESSAGE-ID"));
			log.info("<<consumeEventsFromKafkaDC2>> Message Id received  {} ", messageId);
		} catch (Exception ex) {
			log.error("<<KafkaConsumerService>> <<consumeEventsFromKafkaDC2>> Error Occured{} ", ex.getMessage());

		}
		ack.acknowledge();

	}

	@KafkaListener(topics = "#{'kafka-v2-mx-secure-az-stg-scus.mx-rs-message-transformer'.split(',')}", groupId = "mx-rs-message-transformer-consumer", containerFactory = "kafkaListenerContainerFactoryDC2", autoStartup = "false")
	public void consumeEventsFromKafkaDC2Replica(String riversandJson, Acknowledgment ack,
			MessageHeaders messageHeaders) {
		log.info("<<consumeEventsFromKafkaDC2Replica>>  The Event Body Received From Kafka is {}", riversandJson);
		try {
			String messageId = String.valueOf(messageHeaders.get("MESSAGE-ID"));
			log.info("<<consumeEventsFromKafkaDC2Replica>> Message Id received  {} ", messageId);
		} catch (Exception ex) {
			log.error("<<KafkaConsumerService>> <<consumeEventsFromKafkaDC2Replica>> Error Occured{} ",
					ex.getMessage());

		}
		ack.acknowledge();

	}

}

//kafka-v2-mx-secure-az-stg-wus.mx-rs-message-transformer
//kafka-v2-mx-secure-az-stg-scus.mx-rs-message-transformer
