package com.scheduler.learn;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/*
 * 
 * @EnableScheduling add after @SpringBootApplication to run as SpringbootApplication
 * Otherwise , run as java application only is working
 * Reason might be, Configuration not scanned properly
 * 
 */
@SpringBootApplication
public class SpringSchedulerApplication {

	@Autowired
	TestValueAnno anno;

	public static void main(String[] args) {
		System.out.println("-");
		SpringApplication.run(SpringSchedulerApplication.class, args);
	}

	/*
	 * Every Scheduled Jobs run in a single thread
	 * 
	 * This can be over come by spring.task.scheduling.pool.size=2
	 */

	/*
	 * 
	 * Runs Every Three second irrespective of how much time it takes to complete
	 * the task
	 */

	@Scheduled(initialDelay = 2000L, fixedRate = 3000l)
	void dofixedRate() throws InterruptedException {
		anno.printAnno();
		System.out.println("Printing fixedRate Time " + new Date());
		Thread.sleep(2000l);
		System.out.println("########### Printing fixedRate Time  Awake ###########");
	}

	/*
	 * 
	 * Runs Every Four second
	 */

	@Scheduled(initialDelay = 5000L, fixedDelay = 2000l)
	void dofixedDelay() throws InterruptedException {

		System.out.println("Printing fixedDelay Time " + new Date());
		Thread.sleep(2000l);
		System.out.println("*********** Printing fixedDelay Time  Awake ***********");
	}

	// @Scheduled(initialDelay = 1000L, fixedDelayString = "${scheduler.delay}")
	void dofixedDelayString() throws InterruptedException {
		System.out.println("Printing fixedRate Time " + new Date());
		System.out.println("########### Printing fixedRate Time  Awake ###########");
	}

	/*
	 * https://www.baeldung.com/cron-expressions
	 * 
	 * 
	 */

	// @Scheduled(cron = "05 * * * * MON-FRI")
	void dofixedDelayCron() throws InterruptedException {
		System.out.println("Printing fixedRate Time " + new Date());
		System.out.println("########### Printing fixedRate Time  Awake ###########");
	}

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = { "schedular.enabled" }, havingValue = "true")
class SchedulerConfig {

}
