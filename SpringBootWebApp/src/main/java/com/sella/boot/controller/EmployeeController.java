package com.sella.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sella.boot.model.Employee;

@Controller
public class EmployeeController {

	@RequestMapping("/")
	public ModelAndView getHome() {

		return new ModelAndView("ShowAllEmployees");
	}

	// http://localhost:9090/getEmp?name=awi&emp=tech

	@RequestMapping("getEmp")
	public String getEmp(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		session.setAttribute("name", name);
		String emp = req.getParameter("emp");
		session.setAttribute("emp", emp);
		System.out.println(name);
		System.out.println("hey emp");
		return "home";
	}

	/* Dependency Injection Concept */
	/*
	 * http://localhost:8080/getEmp1?name=awi name is mapped with string name
	 */

	@RequestMapping("getEmp1")
	public String getEmp1(String name, HttpSession session) {
		session.setAttribute("name", name);
		System.out.println("getEmp1" + name);
		System.out.println("hey emp");
		return "home";
	}

	/*
	 * http://localhost:8080/getEmp2?name=awi
	 */
	@RequestMapping("getEmp2")
	public String getEmp2(@RequestParam("name") String myName, HttpSession session) {
		session.setAttribute("name", myName);
		System.out.println("getEmp1" + myName);
		System.out.println("hey emp");
		return "home";
	}

	@RequestMapping("getEmpMv")
	public ModelAndView getEmpMv(@RequestParam("name") String myName) {
		ModelAndView mv = new ModelAndView();
		System.out.println(myName);
		mv.setViewName("home");
		mv.addObject("name", myName);
		return mv;
	}

	/*
	 * http://localhost:8080/getEmpObj?id=1&tech=java&age=22&name=awians kannan
	 * 
	 * header : namee = Rowdy
	 * 
	 *
	 * { "id":1, "tech":"java", "age":"22", "name":"awians kannan" }
	 */

	@RequestMapping("getEmpObj")
	public ModelAndView getEmpObj(@RequestHeader("namee") String name, @RequestBody Employee emp) {
		System.out.println(name);
		ModelAndView mv = new ModelAndView();
		System.out.println(emp);
		mv.setViewName("home");
		mv.addObject("emp", emp);
		return mv;
	}

//	http://localhost:9090/getEmpObj1
//	 { "id":1, "tech":"java", "age":"22", "name":"awians kannan" }
		
	@RequestMapping("getEmpObj1")
	@ResponseBody // Respose body is added here
	public Employee getEmpObj1(@RequestBody Employee emp) {
		ModelAndView mv = new ModelAndView();
		System.out.println(emp);
		mv.setViewName("home");
		mv.addObject("emp", emp);

		return emp;
	}
	
	//Without @ResponseBody -> Error
	@RequestMapping("getEmpObj2")
	public Employee getEmpObj2(@RequestBody Employee emp) {
		System.out.println("<getEmpObj2>");
		ModelAndView mv = new ModelAndView();
		System.out.println(emp);
		mv.setViewName("home");
		mv.addObject("emp", emp);
		return emp;
	}

}
