package com.sams.kakfa.lags.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducerService {

	@Autowired
	@Qualifier("kafkaProducerTemplateDC2")
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String payload, String topic) {
		log.info("<<KafkaProducerService>>  {} {} ", topic, payload);
		kafkaTemplate.send(topic, payload);
	}

	public void sendMessage(String uuid, String payload, String topic) {
		log.info("<<KafkaProducerService>>  {} {} ", topic, payload);
		Message<String> message = MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.MESSAGE_KEY, uuid)
				.setHeader(KafkaHeaders.TOPIC, topic).setHeader("MESSAGE-ID", uuid).build();
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(message);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				handleSuccess(topic, uuid, payload, result);
			}

			@Override
			public void onFailure(Throwable ex) {
				handleFailure(ex);
			}

		});
	}

	private void handleSuccess(String topic, String key, String value, SendResult<String, String> result) {
		log.info("Message Sent SuccessFully to kafka topic : {} for the key : {} and the value : {} , partition : {}",
				topic, key, value, result.getRecordMetadata().partition());
	}

	private void handleFailure(Throwable ex) {
		log.info("Message Sending Failed");

	}
}
