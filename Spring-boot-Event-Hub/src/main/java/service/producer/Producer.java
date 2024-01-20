package service.producer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;

import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Producer {

	// ds
//    private static final String connectionString = "Endpoint=sb://samsclubmxds-eventhub.servicebus.windows.net/;SharedAccessKeyName=RDP;SharedAccessKey=<key>;EntityPath=export";
//    private static final String eventHubName = "export";

	// fs
	
	private static final String connectionString = "Endpoint=sb://samsclubmxfs-eventhub.servicebus.windows.net/;SharedAccessKeyName=RDP;SharedAccessKey=<key>;EntityPath=export";
	private static final String eventHubName = "export";

// Prod
//    private static final String connectionString = "Endpoint=sb://samsclubmx-eventhub.servicebus.windows.net/;SharedAccessKeyName=RDP;SharedAccessKey=<key>;EntityPath=export";
//    private static final String eventHubName = "export";

	public String readFileFromSystemFolder(String filePath) throws IOException {
		log.info("readFileFromSystemFolder {} ", filePath);
		File file = new File(filePath);
		if (file != null) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String data = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			log.info("readFileFromSystemFolder {} ********* JSON Fetched  For File {} ********", filePath);
			return data;
		} else {
			throw new RuntimeException("resource not found");
		}

	}

	public String readFileFromResourceFolder(String jsonPath) {

		String fileName = jsonPath;
		ClassLoader classLoader = this.getClass().getClassLoader();

		InputStream is = classLoader.getResourceAsStream(fileName);
		if (is != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String data = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			log.info("********* JSON Fetched ******** {}", data);
			return data;
		} else {
			throw new RuntimeException("resource not found");
		}

	}

	public void publishEventsFromSysFolder(String jsonPath) throws IOException {
		// create a producer client
		EventHubProducerClient producer = new EventHubClientBuilder().connectionString(connectionString, eventHubName)
				.buildProducerClient();

		// sample events in an array
		byte[] data = readFileFromSystemFolder(jsonPath).getBytes();

		List<com.azure.messaging.eventhubs.EventData> allEvents = Arrays
				.asList(new com.azure.messaging.eventhubs.EventData(data));

		// create a batch
		EventDataBatch eventDataBatch = producer.createBatch();

		for (com.azure.messaging.eventhubs.EventData eventData : allEvents) {
			// try to add the event from the array to the batch
			if (!eventDataBatch.tryAdd(eventData)) {
				// if the batch is full, send it and then create a new batch
				producer.send(eventDataBatch);
				eventDataBatch = producer.createBatch();

				// Try to add that event that couldn't fit before.
				if (!eventDataBatch.tryAdd(eventData)) {
					throw new IllegalArgumentException(
							"Event is too large for an empty batch. Max size: " + eventDataBatch.getMaxSizeInBytes());
				}
			}
		}
		// send the last batch of remaining events
		if (eventDataBatch.getCount() > 0) {
			producer.send(eventDataBatch);
		}
		producer.close();

	}

	public void publishEvents(String jsonPath) {
		// create a producer client
		EventHubProducerClient producer = new EventHubClientBuilder().connectionString(connectionString, eventHubName)
				.buildProducerClient();

		// sample events in an array
		byte[] data = readFileFromResourceFolder(jsonPath).getBytes();

		List<com.azure.messaging.eventhubs.EventData> allEvents = Arrays.asList(
				new com.azure.messaging.eventhubs.EventData(data), new com.azure.messaging.eventhubs.EventData("Test"));

		// create a batch
		EventDataBatch eventDataBatch = producer.createBatch();

		for (com.azure.messaging.eventhubs.EventData eventData : allEvents) {
			// try to add the event from the array to the batch
			if (!eventDataBatch.tryAdd(eventData)) {
				// if the batch is full, send it and then create a new batch
				producer.send(eventDataBatch);
				eventDataBatch = producer.createBatch();

				// Try to add that event that couldn't fit before.
				if (!eventDataBatch.tryAdd(eventData)) {
					throw new IllegalArgumentException(
							"Event is too large for an empty batch. Max size: " + eventDataBatch.getMaxSizeInBytes());
				}
			}
		}
		// send the last batch of remaining events
		if (eventDataBatch.getCount() > 0) {
			producer.send(eventDataBatch);
		}
		producer.close();

	}

	public void publishEventsFromConsole(String event) {
		// create a producer client
		EventHubProducerClient producer = new EventHubClientBuilder().connectionString(connectionString, eventHubName)
				.buildProducerClient();

		// sample events in an array
		byte[] data = event.getBytes();

		List<com.azure.messaging.eventhubs.EventData> allEvents = Arrays
				.asList(new com.azure.messaging.eventhubs.EventData(data));

		// create a batch
		EventDataBatch eventDataBatch = producer.createBatch();

		for (com.azure.messaging.eventhubs.EventData eventData : allEvents) {
			// try to add the event from the array to the batch
			if (!eventDataBatch.tryAdd(eventData)) {
				// if the batch is full, send it and then create a new batch
				producer.send(eventDataBatch);
				eventDataBatch = producer.createBatch();

				// Try to add that event that couldn't fit before.
				if (!eventDataBatch.tryAdd(eventData)) {
					throw new IllegalArgumentException(
							"Event is too large for an empty batch. Max size: " + eventDataBatch.getMaxSizeInBytes());
				}
			}
		}
		// send the last batch of remaining events
		if (eventDataBatch.getCount() > 0) {
			producer.send(eventDataBatch);
		}
		producer.close();

	}
}