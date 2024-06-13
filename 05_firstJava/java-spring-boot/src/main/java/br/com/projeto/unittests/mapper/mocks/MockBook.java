package br.com.projeto.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.projeto.data.vo.v1.BookDTO;
import br.com.projeto.model.Book;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookDTO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> Books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            Books.add(mockEntity(i));
        }
        return Books;
    }

    public List<BookDTO> mockVOList() {
        List<BookDTO> Books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            Books.add(mockVO(i));
        }
        return Books;
    }
    
    public Book mockEntity(Integer number) {
        Book Book = new Book();
        Book.setId(number.longValue());
        Book.setTitulo("Titulo Test" + number);
        Book.setAutor("Autor Test" + number);
        Book.setDataLancamento(new Date());
        Book.setPreco(8D);
        return Book;
    }

    public BookDTO mockVO(Integer number) {
        BookDTO Book = new BookDTO();
        Book.setKey(number.longValue());
        Book.setTitulo("Titulo Test" + number);
        Book.setAutor("Autor Test" + number);
        Book.setDataLancamento(new Date());
        Book.setPreco(8D);
        return Book;
    }

}
