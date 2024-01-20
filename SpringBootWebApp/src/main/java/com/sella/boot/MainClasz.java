package com.sella.boot;

import javax.ws.rs.core.MediaType;

import com.sella.boot.model.Employee;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MainClasz {

	public static void main(String[] args) {
		try {

			Client client = Client.create();
			String url = "http://localhost:8080/getEmpObj";
			String url1 = "http://localhost:8080/getEmpObj1";
			WebResource resource = client.resource(url);
			WebResource resource1 = client.resource(url1);
			Employee json1 = new Employee();
			json1.setAge("1");
			json1.setId(1);
			json1.setName("Awinas");
			json1.setTech("java");

			String json11 = " {\r\n" + "        \"id\": 106,\r\n" + "        \"name\": \"Sp\",\r\n"
					+ "        \"age\": null,\r\n" + "        \"tech\": \"java\",\r\n" + "        \"created\": true\r\n"
					+ "    }";

			/*
			 * ClientResponse response =
			 * resource.type(MediaType.APPLICATION_JSON).header("namee", "AK")
			 * .post(ClientResponse.class, json11); String output =
			 * response.getEntity(String.class);
			 * 
			 * System.out.println(response.getStatus()); System.out.println(output);
			 */

			ClientResponse response = resource1.type(MediaType.APPLICATION_JSON).entity(json11)
					.post(ClientResponse.class);

			// ClientResponse response =
			// resource1.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json1);
			 Employee output = response.getEntity(Employee.class);

			System.out.println(response.getStatus());
			 System.out.println(output);
			/*
			 * RestTemplate restTemplate = new RestTemplate();
			 * 
			 * // ResponseEntity<Employee> result = restTemplate.postForEntity(url1, json1,
			 * // Employee.class); Employee e = restTemplate.postForObject(url1, json1,
			 * Employee.class, ""); System.out.println(e);
			 */
		} catch (Exception e) {
			System.out.println("Err");
			e.printStackTrace();

		}
	}

}
