package br.com.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.Person;
import br.com.projeto.services.PersonServices;

@RestController
@RequestMapping("/api-person")
public class PersonController {
	
	
	@Autowired
	private PersonServices service;

	@GetMapping("{id}")
	public Person getPerson(@PathVariable(value = "id") String id ) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<Person> getAll(){
		return service.findAll();
	}
	
	@PostMapping
	public Person create(@RequestBody Person p) {
		return service.create(p);
	}
	
	@PutMapping
	public Person update(@RequestBody Person p) {
		return service.update(p);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable(value = "id") String id) {
		service.deleteById(id);
	}
}
