package br.com.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.data.vo.v1.PersonVO;
import br.com.projeto.data.vo.v2.PersonVOV2;
import br.com.projeto.services.PersonServices;

@RestController
@RequestMapping("/api-person")
public class PersonController {
	
	
	@Autowired
	private PersonServices service;

	@GetMapping("{id}")
	public PersonVO getPersonVO(@PathVariable(value = "id") String id ) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<PersonVO> getAll(){
		return service.findAll();
	}
	
	@PostMapping
	public PersonVO create(@RequestBody PersonVO p) {
		return service.create(p);
	}

	@PostMapping("/v2")
	public PersonVOV2 createV2(@RequestBody PersonVOV2 p) {
		return service.createV2(p);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO p) {
		return service.update(p);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
