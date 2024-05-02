package br.com.projeto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.projeto.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	private List<Person> lista = new ArrayList<>();
	
	
	public Person create(Person p) {
		logger.info("---------------------------------------------------------");
		logger.info("Método criar person - firstName:"+p.getFirstName());
		logger.info("---------------------------------------------------------");
		p.setId(counter.incrementAndGet());
		lista.add(p);
		
		return p;
	}

	public Person update(Person p) {
		logger.info("---------------------------------------------------------");
		logger.info("Método atualizar person - firstName:"+p.getFirstName());
		logger.info("---------------------------------------------------------");
		
		lista.remove(Integer.parseInt(p.getId().toString())-1);
		lista.add(p);
		
		lista.add(p);
		
		return p;
	}
	
	public void deleteById(String id) {
		logger.info("---------------------------------------------------------");
		logger.info("Método remover person - ID:"+id);
		logger.info("---------------------------------------------------------");

		lista.remove(Integer.parseInt(id)+1);
		
	}
	
	public Person findById(String id) {
		logger.info("---------------------------------------------------------");
		logger.info("Método buscar person - ID:"+id);
		logger.info("---------------------------------------------------------");
		
		return lista.get(Integer.parseInt(id)-1);
	}

	
	public List<Person> findAll(){
		
		logger.info("---------------------------------------------------------");
		logger.info("Método buscar Todos");
		logger.info("---------------------------------------------------------");

		Person person1 =  new Person();
		person1.setFirstName("Fábio");
		person1.setId(counter.incrementAndGet());
		person1.setLastName("Mário");
		person1.setGender("M");
		person1.setEndereco("Olinda - PE");
		
		Person person2 =  new Person();
		person2.setFirstName("João");
		person2.setId(counter.incrementAndGet());
		person2.setLastName("Silva");
		person2.setGender("M");
		person2.setEndereco("Recife - PE");
		
		Person person3 =  new Person();
		person3.setFirstName("Gerson");
		person3.setId(counter.incrementAndGet());
		person3.setLastName("Karla");
		person3.setGender("F");
		person3.setEndereco("Jaboatão - PE");
		
		Person person4 =  new Person();
		person4.setFirstName("Maria");
		person4.setId(counter.incrementAndGet());
		person4.setLastName("Carla");
		person4.setGender("F");
		person4.setEndereco("BH - MG");
		
		Person person5 =  new Person();
		person5.setFirstName("Juliana");
		person5.setId(counter.incrementAndGet());
		person5.setLastName("Maria");
		person5.setGender("F");
		person5.setEndereco("Salvador - BA");
		
		Person person7 =  new Person();
		person7.setFirstName("Saulo");
		person7.setId(counter.incrementAndGet());
		person7.setLastName("Vasque");
		person7.setGender("M");
		person7.setEndereco("Brasília - DF");
		
		Person person6 =  new Person();
		person6.setFirstName("Junior");
		person6.setId(counter.incrementAndGet());
		person6.setLastName("Costa");
		person6.setGender("M");
		person6.setEndereco("Palmas - TO");
		
		Person person8 =  new Person();
		person8.setFirstName("Rodrigo");
		person8.setId(counter.incrementAndGet());
		person8.setLastName("Freire");
		person8.setGender("M");
		person8.setEndereco("Recife - PE");
		
		Person person9 =  new Person();
		person9.setFirstName("Paula");
		person9.setId(counter.incrementAndGet());
		person9.setLastName("Dantas");
		person9.setGender("F");
		person9.setEndereco("Maceió - AL");
		
		Person person10 =  new Person();
		person10.setFirstName("Henrique");
		person10.setId(counter.incrementAndGet());
		person10.setLastName("Silva");
		person10.setGender("M");
		person10.setEndereco("Jaboatão - PE");
		
		lista.add(person1);
		lista.add(person2);
		lista.add(person3);
		lista.add(person4);
		lista.add(person5);
		lista.add(person6);
		lista.add(person7);
		lista.add(person8);
		lista.add(person9);
		lista.add(person10);
		
		return lista;
		
	}
}
