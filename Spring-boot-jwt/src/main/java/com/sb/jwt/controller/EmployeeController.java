package com.sb.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.jwt.model.Employee;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {

	Map<Integer, Employee> employees = new HashMap<>();

	@PostConstruct
	public void init() {
		employees.put(1, new Employee(1, 21, 20000, "IT"));
		employees.put(2, new Employee(2, 22, 30000, "IT"));
		employees.put(3, new Employee(3, 23, 40000, "IT"));
		employees.put(4, new Employee(4, 24, 50000, "IT"));
		employees.put(5, new Employee(5, 25, 60000, "IT"));
	}

	
	@GetMapping(path = "/employee/{id}")
	public Employee get(@PathVariable("id") Integer id) {
		log.info("{}",id);
		return employees.get(id);
	}

	
	
	@PostMapping(path = "/employee/")
	public void post(Employee employee) {
		log.info("{}",employee);
		employees.put(employee.getId(), employee);
	}

}
