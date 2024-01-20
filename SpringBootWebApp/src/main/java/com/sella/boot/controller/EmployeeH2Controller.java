package com.sella.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sella.boot.model.Employee;
import com.sella.boot.repo.EmployeeCRUDRepo;
import com.sella.boot.repo.SpringDataRestJPARepo;

/*
Spring boot
MVC
JPA
H2*/

//http://localhost:9090/h2-console

@RestController // (@Controller + @ResponseBody)
public class EmployeeH2Controller {

	@Autowired
	EmployeeCRUDRepo repo;

	@Autowired
	SpringDataRestJPARepo jparepo;

	@RequestMapping("emp")
	public ModelAndView showEmployeeForm() {
		return new ModelAndView("Employee");
	}

	@RequestMapping("addEmployee")
	public Employee addEmployee(Employee emp) {
		repo.save(emp);
		return emp;
	}

	/*
	 * http://localhost:8080/getEmployee?id=1
	 */
	@RequestMapping("getEmployee")
	public Employee getEmployee(Integer id) {
		return repo.findById(id).orElse(new Employee());
	}

	// http://localhost:8080/Employee/104
	@RequestMapping(path = { "Employee/{id}", "Emp/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Employee getEmployeeFromPath(@PathVariable(name = "id") Integer id) {
		// repo.findAll();
		return repo.findById(id).orElse(new Employee());
	}

	/*
	 * http://localhost:8080/getAllEmployee
	 */
	@RequestMapping("getAllEmployee")
	public List<Employee> getAllEmployee() {
		return (List<Employee>) repo.findAll();

	}

	/*
	 * http://localhost:8080/getAllEmployeeJPA
	 */
	@RequestMapping("getAllEmployeeJPA")
	public List<Employee> getAllEmployeeJPA() {
		return jparepo.findAll();

	}

	/*
	 * http://localhost:8080/searchEmployeeByName?name=a
	 */
	@RequestMapping("searchEmployeeByName")
	public List<Employee> searchByName(String name) {
		List<Employee> empList = new ArrayList<>();
		empList = (List<Employee>) repo.findAll();
		if (name == null || name == "") {
			return empList;
		} else {
			return empList.stream().filter((e) -> e.getName().startsWith(name)).collect(Collectors.toList());
		}

	}

	/*
	 * http://localhost:8080/deleteEmployee?eid=1 //QueryParam
	 */
	@RequestMapping("deleteEmployee")
	public String deleteEmployee(Integer eid) {
		repo.deleteById(eid);
		return "deleted";
	}

	/*
	 * http://localhost:8080/findEmployeeByTech?tech=java
	 */
	@RequestMapping("findEmployeeByTech")
	public List<Employee> findEmployeeByTech(String tech) {
		return repo.findBytech(tech);
	}

	/*
	 * http://localhost:8080/findEmployeeIdGt?id=104
	 */
	@RequestMapping("findEmployeeIdGt")
	public List<Employee> findEmployeeWhereIdGT(Integer id) {
		List<Employee> empList = new ArrayList<>();
		empList.addAll(repo.findByIdGreaterThan(id));
		empList.addAll(repo.getEmployeeIdGT(id));
		return empList;
	}

	// Mapping Types //Accepts Only that request Type

	@GetMapping("getMEmployee/{id}")
	public Employee getMappingEmployee(@PathVariable("id") Integer id) {
		return repo.findById(id).orElse(new Employee());
	}

	/*
	 * http://localhost:8080/saveEmployee
	 * 
	 * {"name": "kannan", "age": null, "tech": "db" }
	 */
	@PostMapping("saveEmployee")
	// @CrossOrigin(origins = "http://localhost:8080")
	public Employee saveEmployee(@RequestBody Employee emp) {
		repo.save(emp);
		return emp;
	}

	/* Save or update */
	/*
	 * http://localhost:8080/updateEmployee
	 * 
	 * {"name": "kannan", "age": null, "tech": "db" }
	 */
	@PutMapping("updateEmployee")
	public Employee updateEmployee(@RequestBody Employee emp) {
		repo.save(emp);
		return emp;
	}

	/*
	 * http://localhost:8080/deleteEmployeee/101
	 */
	@DeleteMapping("deleteEmployeee/{id}")
	public String deleteEmployeee(@PathVariable("id") Integer eid) {
		repo.deleteById(eid);
		return "deleted";
	}

	/*
	 * http://localhost:8080/checkanddeleteEmployeee/101
	 */
	//@DeleteMapping("deleteEmployeee/{id}")
	public String checkanddeleteEmployeee(@PathVariable("id") Integer eid) {
		Employee e = jparepo.getOne(eid);
		jparepo.delete(e);
		return "deleted";
	}
}
