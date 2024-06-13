package br.com.projeto.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import br.com.projeto.controllers.BookController;
import br.com.projeto.data.vo.v1.BookDTO;
import br.com.projeto.exceptions.ResourceNotFoundException;
import br.com.projeto.mapper.DozerMapper;
import br.com.projeto.repositories.BookRepository;


@Service
public class BookService {

	public final BookRepository repository;
	
	

	public BookService(BookRepository repository) {
		this.repository = repository;
	}



	public BookDTO findById(Long id) {
		
		var entity = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Não encontrado!"));
		BookDTO dto = DozerMapper.parseObject(entity, BookDTO.class);
		
		dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

		
		
		return dto;
	}
	
	
}
