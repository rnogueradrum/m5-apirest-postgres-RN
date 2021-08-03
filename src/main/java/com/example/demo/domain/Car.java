package com.example.demo.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String manufacturer;
	
	private String model; //CONTAINS
	private Double cc;
	private Integer doors;
	
	private Integer year; // IN
	
	//@Column("release_Date")
	private LocalDate releaseDate; // Between
	
	private Boolean available; // True or False
	
	public Car() {}

//	public Car(Long id, String manufacturer, String model, Double cc, Integer doors, Integer year, LocalDate releaseDate, Boolean available) {
//		super();
//		this.id = id;
//		this.manufacturer = manufacturer;
//		this.model = model;
//		this.cc = cc;
//		this.doors = doors;
//	    this.year = year;
//  	this.releaseDate = releaseDate;
//		this.available = available;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getCc() {
		return cc;
	}

	public void setCc(Double cc) {
		this.cc = cc;
	}

	public Integer getDoors() {
		return doors;
	}

	public void setDoors(Integer doors) {
		this.doors = doors;
	}
	
	

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", cc=" + cc + ", doors="
				+ doors + ", year=" + year + ", releaseDate=" + releaseDate + ", Available=" + available + "]";
	}

	

	
	
	

}
