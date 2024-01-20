package com.scheduler.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestValueAnno {

	@Value("${clic.cron}")
	private String appName;
	
	void printAnno() {
		System.out.println(appName);
	}
}
