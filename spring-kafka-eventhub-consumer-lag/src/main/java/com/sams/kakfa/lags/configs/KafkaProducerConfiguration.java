package com.sams.kakfa.lags.configs;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
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

@Configuration
public class KafkaProducerConfiguration {

	// GDSN
//	private String bootstrapServers ="kafka-1048092748-5-1325423766.scus.kafka-v2-shared4-stg.ms-df-messaging.stg-az-southcentralus-9.prod.us.walmart.net:9092";

	// Both
//	private String bootstrapServers = "kafka-349912662-1-1531027073.cdc.kafka-taas-shared2-pvt-stg.ms-df-messaging.cdcprod03.prod.walmart.com:9093,kafka-349912674-1-1531027174.cdc.kafka-taas-shared2-pvt-stg.ms-df-messaging.cdcprod04.prod.walmart.com:9093,kafka-349912752-1-1531027275.cdc.kafka-taas-shared2-pvt-stg.ms-df-messaging.cdcprod02.prod.walmart.com:9093,kafka-1262817395-1-1531026170.edc.kafka-taas-shared2-pvt-stg.ms-df-messaging.prod-edc01.prod.walmart.com:9093,kafka-1262818591-1-1531026271.edc.kafka-taas-shared2-pvt-stg.ms-df-messaging.prod-edc02.prod.walmart.com:9093,kafka-1262819024-1-1531026069.edc.kafka-taas-shared2-pvt-stg.ms-df-messaging.prod-edc03.prod.walmart.com:9093";
	
	//cdc
//	private String bootstrapServers = "kafka-349912662-1-1531027073.cdc.kafka-taas-shared2-pvt-stg.ms-df-messaging.cdcprod03.prod.walmart.com:9093,kafka-349912674-1-1531027174.cdc.kafka-taas-shared2-pvt-stg.ms-df-messaging.cdcprod04.prod.walmart.com:9093,kafka-349912752-1-1531027275.cdc.kafka-taas-shared2-pvt-stg.ms-df-messaging.cdcprod02.prod.walmart.com:9093";
	//edc
//	private String bootstrapServers = "kafka-1262817395-1-1531026170.edc.kafka-taas-shared2-pvt-stg.ms-df-messaging.prod-edc01.prod.walmart.com:9093,kafka-1262818591-1-1531026271.edc.kafka-taas-shared2-pvt-stg.ms-df-messaging.prod-edc02.prod.walmart.com:9093,kafka-1262819024-1-1531026069.edc.kafka-taas-shared2-pvt-stg.ms-df-messaging.prod-edc03.prod.walmart.com:9093";
	//prod eus2
//	private String bootstrapServers = "kafka-942422817-1-1813113107.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-2-1813113110.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-3-1813113113.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-4-1813113116.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-5-1813113119.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-6-1813113122.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-7-1813113125.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-8-1813113128.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093,kafka-942422817-9-1813113131.eus2.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-eastus2-15.prod.us.walmart.net:9093";
	//prod scus2
	private String bootstrapServers = "kafka-1052450285-1-1813111040.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-2-1813111043.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-3-1813111046.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-4-1813111049.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-5-1813111052.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-6-1813111055.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-7-1813111058.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-8-1813111061.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093,kafka-1052450285-9-1813111064.scus.kafka-v2-taas-shared6-prod.ms-df-messaging.prod-az-southcentralus-25.prod.us.walmart.net:9093";
	public static final String TRUSTSTORE_LOCATION = "src/main/resources/PRODUCER_TRUST_STORE_PROD.jks";
	public static final String KEYSTORE_LOCATION = "src/main/resources/PRODUCER_KEY_STORE_PROD.jks";
	public static final String SECURITY_PROTOCOL = "SSL";
	public static final String JKS_FILE = "JKS";

	

	private String kafkaTruststorePassword = "<<password>>";
	private String kafkaKeystorePassword = "<<password>>";

//	@Bean
//	public Map<String, Object> producerConfigs() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		return props;
//	}

	@Bean
	public Map<String, Object> producerConfigs() {
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


	private static String getEncodeValue(String loc) {
		String base64 =null;
		try {
			FileInputStream fis = new FileInputStream(loc);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buff = new byte[100000];
			int  read;
			while((read = fis.read(buff)) != -1) {
				baos.write(buff, 0, read);
			}
			byte[] jksBytes = baos.toByteArray();
			fis.close();
			baos.close();
			
			base64 = Base64.getEncoder().encodeToString(jksBytes);
			System.out.println("base64 +" +base64);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return base64;
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
}
