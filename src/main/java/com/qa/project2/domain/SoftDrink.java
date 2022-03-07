package com.qa.project2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SoftDrink {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	
	public SoftDrink() {
		super();
	}

	public SoftDrink(Integer id, String brand, String name, String description) {
		super();
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SoftDrink [id=" + id + ", brand=" + brand + ", name=" + name + ", description=" + description + "]";
	}


}
