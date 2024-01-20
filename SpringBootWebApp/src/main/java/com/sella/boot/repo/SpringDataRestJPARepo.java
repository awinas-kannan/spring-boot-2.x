package com.sella.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sella.boot.model.Employee;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface SpringDataRestJPARepo extends JpaRepository<Employee, Integer> {

}
