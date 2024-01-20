package com.sella.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sella.boot.model.Employee;

public interface EmployeeCRUDRepo extends CrudRepository<Employee, Integer> {

	List<Employee> findBytech(String tech);

	@Query("From Employee where id > ?1")
	List<Employee> getEmployeeIdGT(Integer id);

	List<Employee> findByIdGreaterThan(Integer id);

}
