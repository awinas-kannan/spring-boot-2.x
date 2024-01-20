package com.sams.mx.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KafkaProducerConfigurationV2 {

	// prod eus2
	private String bootstrapServersSCUS = "kafka-1301504412-1-1378657731.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-2-1378657734.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-3-1378657737.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-4-1378657740.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-5-1378657743.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-6-1378657746.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-7-1378657749.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-8-1378657752.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093,kafka-1301504412-9-1378657755.scus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-southcentralus-10.prod.us.walmart.net:9093";
	// prod scus2
	private String bootstrapServersWUS = "kafka-980199677-1-1378655726.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-2-1378655729.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-3-1378655732.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-4-1378655735.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-5-1378655738.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-6-1378655741.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-7-1378655744.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-8-1378655747.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093,kafka-980199677-9-1378655750.wus.kafka-v2-mx-secure-az-stg.ms-df-messaging.stg-az-westus-8.prod.us.walmart.net:9093";
	
	public static final String TRUSTSTORE_LOCATION = "/etc/secrets/msg_tp_consumertruststore.jks";
	public static final String KEYSTORE_LOCATION = "/etc/secrets/msg_tp_consumerkeystore.jks";
	public static final String SECURITY_PROTOCOL = "SSL";
	public static final String JKS_FILE = "JKS";
	private String kafkaTruststorePassword = "<<password>>$";
	private String kafkaKeystorePassword = "<<password>>$";

	public Map<String, Object> producerConfigs(String bootstrapServers) {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//	    config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
//	    config.put(ProducerConfig.RETRIES_CONFIG, ccmAppConfig.getKafkaRetries());
//	    config.put(ProducerConfig.ACKS_CONFIG, ccmAppConfig.getKafkaAcks());
		config.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, JKS_FILE);
		config.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, JKS_FILE);

		config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, SECURITY_PROTOCOL);

		config.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, KEYSTORE_LOCATION);
		config.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, TRUSTSTORE_LOCATION);
		config.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaTruststorePassword);
		config.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, kafkaKeystorePassword);
		config.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaKeystorePassword);
		return config;
	}

	public ProducerFactory<String, String> producerFactory(String bootstrapServers) {
		log.debug("<<KafkaProducerConfiguration>> <<producerFactory>>");
		return new DefaultKafkaProducerFactory<>(producerConfigs(bootstrapServers));
	}

	@Bean(value = "kafkaProducerTemplateDC1")
	public KafkaTemplate<String, String> kafkaProducerTemplateDC1() {
		log.debug("<<KafkaProducerConfiguration>> <<kafkaTemplateDC1>>");
		return new KafkaTemplate<>(producerFactory(bootstrapServersSCUS));
	}

	@Bean(value = "kafkaProducerTemplateDC2")
	public KafkaTemplate<String, String> kafkaProducerTemplateDC2() {
		log.debug("<<KafkaProducerConfiguration>> <<kafkaProducerTemplateDC2>>");
		return new KafkaTemplate<>(producerFactory(bootstrapServersWUS));
	}

}
