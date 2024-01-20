package com.sella.boot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//default name will be laptop
@Component(value = "lap")

//@Scope(value = "prototype")
@Entity
@Table(name = "Laptop")
public class Laptop {
	@Id
	private Integer laptopId;
	private String brand;
	private String ram;
	private String harddiskSize;
	private static Integer count = 0;

	public Laptop() {
		System.out.println("Laptop Object Created " + (++count));
	}

	public Integer getLaptopId() {
		return laptopId;
	}

	public void setLaptopId(Integer laptopId) {
		this.laptopId = laptopId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHarddiskSize() {
		return harddiskSize;
	}

	public void setHarddiskSize(String harddiskSize) {
		this.harddiskSize = harddiskSize;
	}

	@Override
	public String toString() {
		return "Laptop [laptopId=" + laptopId + ", brand=" + brand + ", ram=" + ram + ", harddiskSize=" + harddiskSize
				+ "]";
	}

	public void compile() {
		System.out.println("Laptop Working ...!!");
	}

}
