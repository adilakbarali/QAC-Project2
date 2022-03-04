package com.qa.project2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SoftDrinks {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private String flavour;
	@Column(nullable = false)
	private Double sizeLitres;
	
	public SoftDrinks() {
		super();
	}

	public SoftDrinks(Integer id, String brand, String flavour, Double sizeLitres) {
		super();
		this.id = id;
		this.brand = brand;
		this.flavour = flavour;
		this.sizeLitres = sizeLitres;
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

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public Double getSizeLitres() {
		return sizeLitres;
	}

	public void setSizeLitres(Double sizeLitres) {
		this.sizeLitres = sizeLitres;
	}
	
	
	
}
