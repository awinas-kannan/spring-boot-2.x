package it.sella.boot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "emp")
@Scope(value = "prototype")
public class Employee {

	private Integer id;
	private String name;
	private String age;
	private String tech;
	private static Integer count = 0;

	@Autowired
	@Qualifier(value = "lap")
	private Laptop laptop;

	public Employee() {
		System.out.println("Employee Object Created " + (++count));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", tech=" + tech + ", laptop=" + laptop + "]";
	}

	public Boolean isCreated() {
		return true;
	}

	public void work() {
		laptop.compile();
	}
}
