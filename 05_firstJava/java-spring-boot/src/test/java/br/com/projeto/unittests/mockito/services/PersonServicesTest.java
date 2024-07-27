package br.com.projeto.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.List;
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

import br.com.projeto.data.vo.v1.PersonVO;
import br.com.projeto.model.Person;
import br.com.projeto.repositories.PersonRepository;
import br.com.projeto.services.PersonServices;
import br.com.projeto.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;

	@InjectMocks
	private PersonServices service;

	@Mock
	private PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {

		input = new MockPerson();
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testPersonServices() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1); 
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		service = new PersonServices(repository, null);
		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertEquals("Addres Test1", result.getEndereco());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreateV2() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		service = new PersonServices(repository, null);
		service.deleteById("1");
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		service = new PersonServices(repository, null);
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		System.out.println(result.getLinks());

		assertTrue(result.toString().contains("</api-person/1>;rel=\"self\""));
		assertEquals("Addres Test1", result.getEndereco());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());

	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		service = new PersonServices(repository, null);
//		var people = service.findAll();
//
//		assertNotNull(people);
//		assertEquals(14, people.size());
//
//		var personOne = people.get(1);
//
//		assertNotNull(personOne);
//		assertNotNull(personOne.getKey());
//		assertNotNull(personOne.getLinks());
//
//		assertTrue(personOne.toString().contains("</api-person/1>;rel=\"self\""));
//		assertEquals("Addres Test1", personOne.getEndereco());
//		assertEquals("First Name Test1", personOne.getFirstName());
//		assertEquals("Last Name Test1", personOne.getLastName());
//		assertEquals("Female", personOne.getGender());
//
//		var personFour = people.get(4);
//
//		assertNotNull(personFour);
//		assertNotNull(personFour.getKey());
//		assertNotNull(personFour.getLinks());
//
//		assertTrue(personFour.toString().contains("</api-person/4>;rel=\"self\""));
//		assertEquals("Addres Test4", personFour.getEndereco());
//		assertEquals("First Name Test4", personFour.getFirstName());
//		assertEquals("Last Name Test4", personFour.getLastName());
//		assertEquals("Male", personFour.getGender());
//
//		var personSeven = people.get(7);
//
//		assertNotNull(personSeven);
//		assertNotNull(personSeven.getKey());
//		assertNotNull(personSeven.getLinks());
//
//		assertTrue(personSeven.toString().contains("</api-person/7>;rel=\"self\""));
//		assertEquals("Addres Test7", personSeven.getEndereco());
//		assertEquals("First Name Test7", personSeven.getFirstName());
//		assertEquals("Last Name Test7", personSeven.getLastName());
//		assertEquals("Female", personSeven.getGender());
	}

}
