package br.com.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
