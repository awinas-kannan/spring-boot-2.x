package com.producer.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.producer.kafka.model.User;

@Configuration
public class KafkaProducerConfig {

	@Bean
	public ProducerFactory<String, String> kafkaStirngProducerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configs);
	}

	@Bean
	public ProducerFactory<String, User> kafkaUserProducerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configs);
	}

	@Bean
	public KafkaTemplate<String, String> stringKafkaTemp() {
		System.out.println("calling .... " + "stringKafkaTemp");
		return new KafkaTemplate<>(kafkaStirngProducerFactory());

	}

	@Bean
	public KafkaTemplate<String, User> userKafkaTemp() {
		System.out.println("calling .... " + "userKafkaTemp");
		return new KafkaTemplate<>(kafkaUserProducerFactory());

	}

}
