package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.domain.Car;

public interface CarService {
	
	// Spring repository methods
	Car save(Car car);
	
	List<Car> findAll();
	
	Optional<Car> findById(Long id);
	
	Long count();
	
	void deleteById(Long id);
	
	void deleteAll();
	
	void deleteAll(List<Car> cars);
	
	// Custom methods
	
	List<Car> findByDoors(Integer doors);
	
    List<Car>findByManufacturerAndModel(String manufacturer, String model);
	
	List<Car>findByDoorsGreaterThanEqual(Integer doors);
	
	List<Car>findByModelContaining(String model);
	
	List<Car>findByYearIn(List<Integer> years);
	
	List<Car>findByYearBetween(Integer startYear, Integer endYear);
	
	List<Car>findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
	
	List<Car>findByAvailableTrue();
	
	Long deleteAllByAvailableFalse();
	
	
	
}
