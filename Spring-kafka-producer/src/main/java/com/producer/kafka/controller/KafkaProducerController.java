package com.producer.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.kafka.model.User;

@RestController
@RequestMapping("/kafka/producer")
public class KafkaProducerController {

	/*
	 * For Default String ,Strin No Config is required
	 * 
	 * But if we specify some config for custom type ( User)
	 * then  Add for String type seperatly in config
	 * 
	 */
	
	@Autowired
	KafkaTemplate<String, String> kafkatemplate;

	@Autowired
	KafkaTemplate<String, User> kafkaJsonemplate;

	private static final String STRING_TOPIC = "kafka_string_producer";
	private static final String JSON_TOPIC = "kafka_json_producer";

	@GetMapping("/{Message}")
	public String stringProducer(@PathVariable("Message") String message) {
		String DATA = message;
		kafkatemplate.send(STRING_TOPIC, DATA);
		return "Message Published";
	}

//	http://localhost:8081/kafka/producer/json/awi/java/25

	/*
	 * Without Kafka Config following error occurs
	 * 
	 * 2021-09-26 16:54:30.263[0;39m [31mERROR[0;39m [35m26255[0;39m
	 * [2m---[0;39m [2m[nio-8081-exec-1][0;39m
	 * [36mo.a.c.c.C.[.[.[/].[dispatcherServlet] [0;39m [2m:[0;39m
	 * Servlet.service() for servlet [dispatcherServlet] in context with path []
	 * threw exception [Request processing failed; nested exception is
	 * org.apache.kafka.common.errors.SerializationException: Can't convert value of
	 * class com.producer.kafka.model.User to class
	 * org.apache.kafka.common.serialization.StringSerializer specified in
	 * value.serializer] with root cause
	 * 
	 * java.lang.ClassCastException: class com.producer.kafka.model.User cannot be
	 * cast to class java.lang.String (com.producer.kafka.model.User is in unnamed
	 * module of loader 'app'; java.lang.String is in module java.base of loader
	 * 'bootstrap') at org.a
	 * 
	 */
	@GetMapping("/json/{name}/{tech}/{age}")
	public String jsonProducer(@PathVariable("name") final String name, @PathVariable("age") final int age,
			@PathVariable("tech") final String tech) {
		User user = new User(name, tech, age);
		kafkaJsonemplate.send(JSON_TOPIC, user);
		return "Message Published";
	}
}
