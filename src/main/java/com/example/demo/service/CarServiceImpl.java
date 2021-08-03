package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.rest.CarController;

@Service
public class CarServiceImpl implements CarService {
	
	
	private static final Integer MIN_DOORS = 0;

	private final Logger log = LoggerFactory.getLogger(CarController.class);
	
	private CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		
		this.carRepository = carRepository;		
		
	}
	
	/**
	 * 
	 * save
	 * 
	 * @return a response entity with car
	 */
	@Override
	public Car save(Car car) {
		log.info("REST request to create a new car");
		if (car == null) {
			log.warn("Trying to create null car");
			return null;
		}
		Car carDB = this.carRepository.save(car);
		// Enviar notificaciones
		//this.notificationService(NotificationType.CREATION, car);
		
		return carDB;
	}

	@Override
	public List<Car> findAll() {
		log.info("Executing findAll Cars");
		return this.carRepository.findAll();
	}

	@Override
	public Optional<Car> findById(Long id) {
		log.info("Executing findOne");
		return this.carRepository.findById(id);
	}
	
	@Override
	public List<Car> findByDoors(Integer doors) {
		if(doors.equals(MIN_DOORS)) {
			log.warn("Trying to search less than allowed doors");
			return new ArrayList<Car>();
		}
			return this.carRepository.findByDoors(doors);
	}
	
	@Override
	public Long count() {
		log.info("Get Total number of cars");
		return this.carRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		log.info("Deleting car by id");
		if (id == null || id < 0 || id == 0)
			return;
		
		this.carRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll() {
		log.info("Deleting cars");
		this.carRepository.deleteAll();
	}

	@Override
	public void deleteAll(List<Car> cars) {
		//if (cars == null || cars.isEmpty()) Se puede hacer con CollectionUtils.isEmpty
		if (CollectionUtils.isEmpty(cars)) {
			log.warn("Trying to delete an invalid cars list");
			return;
		}
		
		this.carRepository.deleteAll(cars);
	}

	@Override
	public List<Car> findByManufacturerAndModel(String manufacturer, String model) {
		if(!StringUtils.hasLength(model) || !StringUtils.hasLength(model))
			return new ArrayList<>();
		
		return this.carRepository.findByManufacturerAndModel(manufacturer, model);
	}

	@Override
	public List<Car> findByDoorsGreaterThanEqual(Integer doors) {
		if(doors == null || doors < 0)
			return new ArrayList<>();
		return this.carRepository.findByDoorsGreaterThanEqual(doors);
	}

	@Override
	public List<Car> findByModelContaining(String model) {
		
		return this.carRepository.findByModelContaining(model);
	}

	@Override
	public List<Car> findByYearIn(List<Integer> years) {
		
		return this.carRepository.findByYearIn(years);
	}

	@Override
	public List<Car> findByYearBetween(Integer startYear, Integer endYear) {
		
		return this.carRepository.findByYearBetween(startYear, endYear);
	}

	@Override
	public List<Car> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate) {
		
		return this.carRepository.findByReleaseDateBetween(startDate, endDate);
	}

	@Override
	public List<Car> findByAvailableTrue() {
		
		return this.carRepository.findByAvailableTrue();
	}

	@Override
	public Long deleteAllByAvailableFalse() {
		
		return this.carRepository.deleteAllByAvailableFalse();
	}
	
	

}
