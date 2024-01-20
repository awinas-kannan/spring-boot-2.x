package com.sams.mx.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducerService {

	private String topic = "mx-rs-message-transformer";

	@Autowired
	@Qualifier("kafkaProducerTemplateDC1")
	private KafkaTemplate<String, String> kafkaProducerTemplateDC1;

	@Autowired
	@Qualifier("kafkaProducerTemplateDC2")
	private KafkaTemplate<String, String> kafkaProducerTemplateDC2;

	private final SecureRandom random = new SecureRandom();

	public void sendMessage(String uuid, String payload) {
		log.info("<<KafkaProducerService>>  Sending Message {} {}", payload);
		try {
			Message<String> message = MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.KEY, uuid)
					.setHeader(KafkaHeaders.TOPIC, topic).setHeader("MESSAGE-ID", uuid).build();
			sendMessageToActiveActive(message);
		} catch (Exception ex) {
			log.error("Error occured while sending to transformer {}", ex.getMessage());

		}
	}

	/**
	 * This method will randomly choose a kafka DC and publish the message
	 * 
	 * @param messageKey
	 * @param inboundJsonStatusInfo
	 *
	 * @param message
	 * @param data
	 */
	private void sendMessageToActiveActive(Message<String> message) {
		if (random.nextBoolean()) {
			sendMessageToKafkaDC1(message);
		} else {
			sendMessageToKafkaDC2(message);
		}
	}

	private void sendMessageToKafkaDC1(Message<String> message) {
		log.info("Producer DC1 {}", message);
		kafkaProducerTemplateDC1.send(message);

	}

	private void sendMessageToKafkaDC2(Message<String> message) {
		log.info("Producer DC2 {}", message);
		kafkaProducerTemplateDC2.send(message);
	}

}
