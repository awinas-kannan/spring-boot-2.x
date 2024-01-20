package com.consumer.kafka.listner;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.consumer.kafka.model.User;

@Service
public class KafkaConsumer {

	private static final String STRING_TOPIC = "kafka_string_producer";
	private static final String JSON_TOPIC = "kafka_json_producer";

	@KafkaListener(topics = { STRING_TOPIC }, groupId = "MY_STRING_GROUP" , containerFactory = "stringKafkaConsumer")
	public void stringListner(String message) {
		System.out.println("<<stringListner>> " + message);
	}

	@KafkaListener(topics = { JSON_TOPIC }, groupId = "MY_JSON_GROUP", containerFactory = "jsonKafkaConsumer")
	public void JSONListner(User message) {
		System.out.println("<<JSONListner>> " + message);
	}
}
