package com.sams.kakfa.lags.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sams.kakfa.lags.service.EventHubProducerService;
import com.sams.kakfa.lags.service.KafkaProducerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka/producer")
@Slf4j
public class KafkaController {

	@Autowired
	KafkaProducerService kafkaProducerService;

	@Autowired
	EventHubProducerService eventHubProducerService;

	private String topicName = "APM0008759-mx-item-mdm-service-DEV-entity-messages";
	List<String> uuid = new ArrayList<>();

	@PostConstruct
	private void init() {

		uuid.addAll(Arrays.asList("1a38c1f2-a784-469a-bcf0-f49d5313f032", "e020da20-2f24-4fe9-a939-bd0c6005fa00",
				"52eff3de-77e0-48f6-ba78-b5eceeb8d288", "17564160-fcb1-4ae0-b750-0afc0eb4d52f",
				"adedcc44-0172-48af-a367-0cdcfedf1731", "0221eceb-2f95-4e3d-b4f9-a236d5cd36f4",
				"67840f6f-052f-49fb-be92-b971957a7d90", "2c8faa0d-0d70-4eec-8adc-0b27f86ae547",
				"3a81614b-8265-4b17-b35f-74d012e9c839", "6920a8b5-c3aa-44f1-a3e1-12deb2cd8d70"));
		log.info("all uuids {} ", uuid);

	}

	@GetMapping("/{Message}")
	public String stringProducer(@PathVariable("Message") String message) {
		for (int i = 0; i < 50; i++) {
			kafkaProducerService.sendMessage(message + i, topicName);
		}
		return "Message  Published";
	}

	@GetMapping("/send/{Message}")
	public String stringProducerTest(@PathVariable("Message") String message) {
		for (String uuid1 : uuid) {
			kafkaProducerService.sendMessage(uuid1, message + "  ---  " + uuid1, topicName);
		}

		return "Message Published";
	}

	@PostMapping("/send/rs")
	public String stringRsProducerTest(@RequestBody String message) {
		eventHubProducerService.sendResponseToRiversand(message);
		return "Message Published";
	}
}
