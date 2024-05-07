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
import br.com.projeto.util.MediaType;

@RestController
@RequestMapping("/api-person")
public class PersonController {
	
	
	@Autowired
	private PersonServices service;

	@GetMapping(value = "{id}", produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
	public PersonVO getPersonVO(@PathVariable(value = "id") String id ) {
		return service.findById(id);
	}
	
	@GetMapping( produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
	public List<PersonVO> getAll(){
		return service.findAll();
	}
	
	@PostMapping( produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
	public PersonVO create(@RequestBody PersonVO p) {
		return service.create(p);
	}

	@PostMapping(value="/v2",  produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 p) {
		return service.createV2(p);
	}
	
	@PutMapping( produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
	public PersonVO update(@RequestBody PersonVO p) {
		return service.update(p);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
