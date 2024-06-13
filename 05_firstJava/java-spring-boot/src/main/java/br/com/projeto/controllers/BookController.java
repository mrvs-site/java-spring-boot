package br.com.projeto.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.data.vo.v1.BookDTO;
import br.com.projeto.services.BookService;

@RestController()
@RequestMapping("/api/book")
public class BookController {

	
	public final BookService service;

	public BookController(BookService service) {
		this.service = service;
	}
	

	@GetMapping("{id}")
	public BookDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
}
