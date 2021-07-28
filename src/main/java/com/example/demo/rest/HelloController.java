package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



// @Controller // Cuando usemos Spring MVC con redirección a plantillas html dentro del proyecto


@RequestMapping("/api") // Enrutado HTTP
@RestController
public class HelloController {
	
	private final Logger log = LoggerFactory.getLogger(HelloController.class);
	
	
	/**
	 * http://localhost:8080/api/hello
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
//		System.out.println("Executing hello world method from syso");
		log.info("Executing hello world method from logger");
//		log.warn("Executing hello world method from logger");
//		log.error("Executing hello world method from logger");
		return "Hola Mundo";
		
	}

}
