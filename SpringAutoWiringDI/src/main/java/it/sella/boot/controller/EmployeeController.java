package it.sella.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

	@RequestMapping("employee")
	public void getEmp() {

		System.out.println("Hey Dude");
	}

}
