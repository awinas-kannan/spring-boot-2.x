package com.sella.boot.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sella.boot.model.Employee;

@RestController
public class RestTemplateController {

	@Autowired
	RestTemplate restTemplate;
	private static final String URL = "http://localhost:9090";
	// Get Request Using Resttemplate Exchange Method
	HttpHeaders headers = new HttpHeaders();

	{
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}
	// Consuming the GET API by using RestTemplate - exchange() method

	/*
	 * http://localhost:9090/rt/getAllEmployee
	 */

	@RequestMapping("rt/getAllEmployee")
	public String getAllEmployee() {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(URL + "/getAllEmployee", HttpMethod.GET, entity, String.class).getBody();

	}

	// Consuming POST API by using RestTemplate - exchange() method

	/*
	 * http://localhost:9090/rt/saveEmployee
	 * 
	 * {"name": "kannan", "age": null, "tech": "db" }
	 */

	@RequestMapping("rt/saveEmployee")
	public String addEmployee(@RequestBody Employee Employee) {
		HttpEntity<Employee> entity = new HttpEntity<Employee>(Employee, headers);
		return restTemplate.exchange(URL + "/saveEmployee", HttpMethod.POST, entity, String.class).getBody();
	}

	/* Save or update */
	/*
	 * http://localhost:8080/updateEmployee
	 * 
	 * {"name": "kannan", "age": null, "tech": "db" }
	 */
	@RequestMapping("rt/updateEmployee")
	public String updateEmployee(@RequestBody Employee emp) {
		HttpEntity<Employee> entity = new HttpEntity<Employee>(emp, headers);
		return restTemplate.exchange(URL + "/updateEmployee", HttpMethod.PUT, entity, String.class).getBody();
	}

	/*
	 * http://localhost:9090/deleteEmployeee/101
	 */
	@RequestMapping("deleteEmployeee/{id}")
	public String deleteEmployeee(@PathVariable("id") Integer eid) {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(URL + "/deleteEmployeee/" + eid, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}

}
