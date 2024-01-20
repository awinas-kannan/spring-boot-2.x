package com.sams.kakfa.lags.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.auth.SecurityProtocol;
import org.apache.kafka.common.security.plain.internals.PlainSaslServer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

@Configuration
@EnableKafka
public class KafkaEventHubConsumerConfiguration {

	
	private String bootstrapServers = "samsclubmxfs-eventhub.servicebus.windows.net:9093";
	private static final String AUTO_OFFSET_CONFIG = "latest";
    
	private static final String GROUP_ID = "test-consumer";
	

	@Bean
	public Map<String, Object> eventhuhconsumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, SecurityProtocol.SASL_SSL.name);
		props.put(SaslConfigs.SASL_MECHANISM, PlainSaslServer.PLAIN_MECHANISM);
		final String saslJaasConfig = String.format(
				"org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";",
				"$ConnectionString", "<<ConnectionString>>");
		props.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_CONFIG);
		
		
		return props;
	}

	@Bean
	public ConsumerFactory<String, String> eventhubconsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(eventhuhconsumerConfigs());
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> eventHubListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.getContainerProperties().setAckMode(AckMode.MANUAL);
		factory.setConsumerFactory(eventhubconsumerFactory());
		return factory;
	}

}
