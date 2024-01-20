package com.sb.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private Integer id;
	private Integer age;
	private Integer salary;
	private String dept;

}
