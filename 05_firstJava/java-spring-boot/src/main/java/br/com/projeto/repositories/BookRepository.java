package br.com.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
