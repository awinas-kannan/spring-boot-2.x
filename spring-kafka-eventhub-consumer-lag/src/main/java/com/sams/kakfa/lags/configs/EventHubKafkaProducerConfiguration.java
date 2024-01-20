package com.sams.kakfa.lags.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.auth.SecurityProtocol;
import org.apache.kafka.common.security.plain.internals.PlainSaslServer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableKafka
@Configuration
public class EventHubKafkaProducerConfiguration {

	private String bootstrapServer ="samsclubmxfs-eventhub.servicebus.windows.net:9093";
	private String connectionString ="<<connectionString>>";
	private String userID ="$ConnectionString";

		
	@Bean
	public Map<String, Object> eventHubkafkaProducerConfigs() {
		log.debug("<<EventHubKafkaProducerConfiguration>> <<eventHubkafkaProducerConfigs>>");
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, SecurityProtocol.SASL_SSL.name);
		final String saslJaasConfig = String.format(
				"org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";",
				userID, connectionString);
		props.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
		props.put(SaslConfigs.SASL_MECHANISM, PlainSaslServer.PLAIN_MECHANISM);
		return props;
	}

	@Bean
	public ProducerFactory<String, String> eventHubkafkaProducerFactory() {
		log.debug("<<EventHubKafkaProducerConfiguration>> <<eventhubproducerFactory>>");
		return new DefaultKafkaProducerFactory<>(eventHubkafkaProducerConfigs());
	}

	@Bean(value = "eventHubkafkaProducerTemplate")
	public KafkaTemplate<String, String> eventHubkafkaProducerTemplate() {
		return new KafkaTemplate<>(eventHubkafkaProducerFactory());
	}

}
