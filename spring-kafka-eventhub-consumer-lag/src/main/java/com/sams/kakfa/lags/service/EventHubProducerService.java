package com.sams.kakfa.lags.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventHubProducerService {

	@Autowired
	@Qualifier("eventHubkafkaProducerTemplate")
	private KafkaTemplate<String, String> kafkaTemplate;

	private String topic = "export";

	public void sendResponseToRiversand(String jsonStr) {
		Message<String> message = MessageBuilder.withPayload(jsonStr).setHeader(KafkaHeaders.TOPIC, topic).build();
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(message);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Successfully sent response to Riversand EventHub for Message id {}",
						jsonStr);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.info("Failed to send response to Riversand EventHub for Message id {} {}",
						jsonStr, ex);
			}
		});
	}

}
