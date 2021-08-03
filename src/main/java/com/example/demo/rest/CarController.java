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
import com.example.demo.dto.CarListDTO;
import com.example.demo.dto.CountDTO;
import com.example.demo.service.CarService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RequestMapping("/api") // Enrutado HTTP
@RestController
public class CarController {


	private final Logger log = LoggerFactory.getLogger(CarController.class);
	
	private CarService carService;

	// Inyección por constructor para facilitar el testing. Por si tenemos que inyectar un mock por ejemplo
		public CarController(CarService carService) {
			this.carService = carService;
		}

		
	/* ============== SPRING CRUD METHODS ============= */
		
	/**
	 * Long count()
	 * @return Número de cars
	 */
	@GetMapping("/cars/count")
	public ResponseEntity<CountDTO> count(){ //Con Long, no devuelve un Json, lo envolvemos en una clase CountDTO
		log.info("REST request recover cars number");
		return ResponseEntity.ok(new CountDTO(this.carService.count()));
		
	}
	
	/**
	 * 
	 * findOne
	 * http://localhost:8080/api/cars
	 * 
	 * @return a response entity with car
	 */
	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> findById(@ApiParam("Busca un coche por su id")@PathVariable("id") Long id) {
		log.info("REST request to find one car");
		
		// Optional envuelve un resultado q puede ser un objeto o valor nulo
		Optional<Car> carOpt = this.carService.findById(id);
		
		if (carOpt.isPresent()) // Comprueba si existe un objeto de tipo Car
			return ResponseEntity.ok(carOpt.get()); // Con get sacamos el objeto del envoltorio
		return ResponseEntity.notFound().build();

		//Opción 2
//		return carOpt
//			.map(
//					car -> ResponseEntity.ok(car)
//				)
//				.orElseGet(
//				() -> ResponseEntity.notFound().build()
//		);
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
		return this.carService.findAll();
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
			
		if (car.getId() != null ) { // Ya existe. Validaciones sencillas aquí, otras complejas, en el Service
			log.warn("Trying to create a new car with existent id");
			return ResponseEntity.badRequest().build();
		}
		
		
		return ResponseEntity.ok(this.carService.save(car));
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
		
		return ResponseEntity.ok(this.carService.save(car));
	}
	
	/**
	 * 
	 * deleteById
	 * http://localhost:8080/api/cars/1L
	 * 
	 * @return a response entity with car
	 */
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		log.info("REST request to delete one car");
		this.carService.deleteById(id);
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
		this.carService.deleteAll();
        return ResponseEntity.noContent().build();
    	
	}
	
	
	/**
	 * 
	 * Se puede utilizar @PostMapping
	 * deleteMany
	 * @Param Lista de cars (ids sólo)
	 * http://localhost:8080/api/cars/
	 * 
	 * @return a response entity with all cars deleted
	 */
	@DeleteMapping("/cars/deletemany")
	public ResponseEntity<Car> deleteMany(@RequestBody CarListDTO carListDto) {
		log.info("REST request to delete a list of cars");
		
		this.carService.deleteAll(carListDto.getCars());
        
		return ResponseEntity.noContent().build();
    	
	}
	
	
	/* ============== CUSTOM CRUD METHODS ============= */
	
	/**
	 * 
	 * findByDoors
	 * http://localhost:8080/api/cars
	 * 
	 * @return a response entity with car
	 */

	@GetMapping("/cars/doors/{doors}")
	@ApiOperation("Busca coches por número de puertas")
	public List<Car> findByDoors(@PathVariable("doors") Integer doors) {
		log.info("REST request to find cars by doors");
		
		return this.carService.findByDoors(doors);   	
	}
	
	
	@GetMapping("/cars/doorsgreater/{doors}")
	@ApiOperation("findByDoorsGreaterThanEqual")
	public List<Car> findByDoorsGreaterThanEqual(@PathVariable Integer doors) {
		log.info("REST request to find cars by doors");
		
		return this.carService.findByDoorsGreaterThanEqual(doors);   	
	}

	
	
}
