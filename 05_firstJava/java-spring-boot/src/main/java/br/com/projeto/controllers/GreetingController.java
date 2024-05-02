package br.com.projeto.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.Greeting;
import br.com.projeto.services.GrettingServices;

@RestController
public class GreetingController {

	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private GrettingServices service;

	@GetMapping
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World")String name ) {
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping(value = "/soma/{v1}/{v2}")
	public Double soma(@PathVariable(value = "v1") String v1,
					   @PathVariable(value = "v2") String v2) throws Exception {
		return  service.soma(v1, v2);
	}
	
	@GetMapping(value = "/sub/{v1}/{v2}")
	public Double sub(@PathVariable(value = "v1") String v1,
			@PathVariable(value = "v2") String v2) throws Exception {
		
		return  service.sub(v1, v2);
	}
	
	@GetMapping(value = "/mult/{v1}/{v2}")
	public Double mult(@PathVariable(value = "v1") String v1,
			@PathVariable(value = "v2") String v2) throws Exception {
		return  service.mult(v1, v2);
	}

	@GetMapping(value = "/div/{v1}/{v2}")
	public Double div(@PathVariable(value = "v1") String v1,
			@PathVariable(value = "v2") String v2) throws Exception {
		return  service.div(v1, v2);
	}
	
	@GetMapping(value = "/raiz/{v1}")
	public Double raiz(@PathVariable(value = "v1") String v1) throws Exception {
		return  service.raiz(v1);
	}

	
}
