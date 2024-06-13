package br.com.projeto.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.projeto.model.Book;
import br.com.projeto.repositories.BookRepository;
import br.com.projeto.services.BookService;
import br.com.projeto.unittests.mapper.mocks.MockBook;
import br.com.projeto.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;

	@InjectMocks
	private BookService service;

	@Mock
	private BookRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {

		input = new MockBook();
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void test() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		service = new BookService(repository);
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		System.out.println(result.getLinks());

		assertTrue(result.toString().contains("</api/book/1>;rel=\"self\""));
		assertEquals("Titulo Test1", result.getTitulo());
		assertEquals("Autor Test1", result.getAutor());
		assertNotNull(result.getDataLancamento());
		assertEquals(8D, result.getPreco());
	}

}
