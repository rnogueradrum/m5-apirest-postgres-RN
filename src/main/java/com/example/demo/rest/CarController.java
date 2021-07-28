package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;


@RequestMapping("/api") // Enrutado HTTP
@RestController
public class CarController {


	private final Logger log = LoggerFactory.getLogger(CarController.class);
	
	private CarRepository carRepository;
	
	
	
	// Inyección por constructor para facilitar el testing. Por si tenemos que inyectar un mock por ejemplo
	public CarController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	

	public CarRepository getCarRepository() {
		return carRepository;
	}

	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	/**
	 * 
	 * findByDoors
	 * http://localhost:8080/api/cars
	 * 
	 * @return a response entity with car
	 */
	@GetMapping("/cars/doors/{doors}")
	public List<Car> findByDoors(@PathVariable("doors") Integer doors) {
		log.info("REST request to find cars by doors");
		
		return this.carRepository.findByDoors(doors);   	
	}

	/**
	 * 
	 * findOne
	 * http://localhost:8080/api/cars
	 * 
	 * @return a response entity with car
	 */
	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> findOne(@PathVariable("id") Long id) {
		log.info("REST request to find one car");
		
		// Optional envuelve un resultado q puede ser un objeto o valor nulo
		Optional<Car> carOpt = this.carRepository.findById(id);
		
		if (carOpt.isPresent()) // Comprueba si existe un objeto de tipo Car
			return ResponseEntity.ok(carOpt.get()); // Con get sacamos el objeto del envoltorio
		return ResponseEntity.notFound().build();
    	
	}
	

	/**
	 * 
	 * findAll
	 * http://localhost:8080/api/cars
	 * 
	 * @return a List with all cars
	 */
	@GetMapping("/cars")
	public List<Car> findAll() {
		
		log.info("Executing CarController method from logger");
		System.out.println("******* Executing CarController from syso *******");
		return this.carRepository.findAll();
	}
	
	
	/**
	 * 
	 * createOne
	 * http://localhost:8080/api/cars
	 * 
	 * @return a response entity with car
	 */
	@PostMapping("/cars")
	public ResponseEntity<Car> createOne(@RequestBody Car car) {
		log.info("REST request to create a new car");
			
		if (car.getId() != null ) { // Ya existe
			log.warn("Trying to create a new car with existent id");
			return ResponseEntity.badRequest().build();
		}
		
		car.setId(null);
		return ResponseEntity.ok(this.carRepository.save(car));
	}
	
	/**
	 * 
	 * updateOne
	 * http://localhost:8080/api/cars
	 * 
	 * @return a response entity with car
	 */
	@PutMapping("/cars")
	public ResponseEntity<Car> updateOne(@RequestBody Car car) {
		log.info("REST request to update a new car");
			
		if (car.getId() == null ) { // No existe
			log.warn("Trying to update a non existent ");
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(this.carRepository.save(car));
	}
	
	/**
	 * 
	 * deleteOne
	 * http://localhost:8080/api/cars/1L
	 * 
	 * @return a response entity with car
	 */
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable("id") Long id) {
		log.info("REST request to delete one car");
		this.carRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    	
	}
	
	/**
	 * 
	 * deleteAll
	 * http://localhost:8080/api/cars/
	 * 
	 * @return a response entity with all cars deleted
	 */
	@DeleteMapping("/cars")
	public ResponseEntity<Car> deleteAll() {
		log.info("REST request to delete all car");
		this.carRepository.deleteAll();
        return ResponseEntity.noContent().build();
    	
	}
	
	
}
