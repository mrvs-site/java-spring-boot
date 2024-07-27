package br.com.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.data.vo.v1.PersonVO;
import br.com.projeto.data.vo.v2.PersonVOV2;
import br.com.projeto.services.PersonServices;
import br.com.projeto.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Pessoa", description = "Endpoints para Gerenciamento de Pessoa")
public class PersonController {
	
	
	@Autowired
	private PersonServices service;

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "{id}", produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
	@Operation(summary = "Procurar Pessoa", description = "Procurar Pessoa", tags = {"Person"})
	public PersonVO getPersonVO(@PathVariable(value = "id") Long id ) {
		return service.findById(id);
	}
	
//	@GetMapping( produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
//	public List<PersonVO> getAll(){
//		return service.findAll();
//	}

	@GetMapping( produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
	public ResponseEntity<Page<PersonVO>> getAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			@RequestParam(value = "campo", defaultValue = "firstName") String campo){

		var sortDirection = direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, campo));
		
		return ResponseEntity.ok(service.findAll(pageable));
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
