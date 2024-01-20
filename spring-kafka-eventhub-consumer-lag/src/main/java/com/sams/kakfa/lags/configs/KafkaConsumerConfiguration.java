package com.sams.kakfa.lags.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

	private String bootstrapServers = "kafka-1052450285-1-1813111040.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-2-1813111043.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-3-1813111046.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-4-1813111049.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-5-1813111052.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-6-1813111055.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-7-1813111058.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-8-1813111061.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-9-1813111064.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093";
	private static final String AUTO_OFFSET_CONFIG = "latest";

	private static final String GROUP_ID = "APM0008759-Consumer-1";
	public static final String TRUSTSTORE_LOCATION = "src/main/resources/CONSUMER_TRUST_STORE_PROD.jks";
	public static final String KEYSTORE_LOCATION = "src/main/resources/CONSUMER_KEY_STORE_PROD.jks";
	public static final String SECURITY_PROTOCOL = "SSL";
	public static final String JKS_FILE = "JKS";

	private String kafkaTruststorePassword = "<<password>>";
	private String kafkaKeystorePassword = "<<password>>";

	@Bean
	public Map<String, Object> stratikafkaconsumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		props.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, JKS_FILE);
		props.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, JKS_FILE);
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, SECURITY_PROTOCOL);
		props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, KEYSTORE_LOCATION);
		props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, TRUSTSTORE_LOCATION);

		props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaTruststorePassword);
		props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, kafkaKeystorePassword);
		props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaKeystorePassword);

		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_CONFIG);

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> stratiKafkaconsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(stratikafkaconsumerConfigs());
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(stratiKafkaconsumerFactory());
		return factory;
	}

}
