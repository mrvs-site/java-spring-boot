package br.com.projeto.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.projeto.data.vo.v1.PersonVO;
import br.com.projeto.data.vo.v2.PersonVOV2;
import br.com.projeto.exceptions.ResourceNotFoundException;
import br.com.projeto.mapper.DozerMapper;
import br.com.projeto.mapper.custom.PersonMapper;
import br.com.projeto.model.Person;
import br.com.projeto.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	private final PersonRepository repository;
	private final PersonMapper mapper;

	public PersonServices(PersonRepository repository, PersonMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public PersonVO create(PersonVO p) {
		logger.info("---------------------------------------------------------");
		logger.info("Método criar PersonVO - firstName:"+p.getFirstName());
		logger.info("---------------------------------------------------------");

		
		var entity = DozerMapper.parseObject(p, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 p) {
		logger.info("---------------------------------------------------------");
		logger.info("Método criar PersonVOV2 - firstName:"+p.getFirstName());
		logger.info("---------------------------------------------------------");
		
		
		var entity = mapper.convertVoToEtity(p);
		var vo = mapper.convertEtityToVo(repository.save(entity));
		
		return vo;
	}

	public PersonVO update(PersonVO p) {
		logger.info("---------------------------------------------------------");
		logger.info("Método atualizar PersonVO - firstName:"+p.getFirstName());
		logger.info("---------------------------------------------------------");
		
		var entity = repository.findById(p.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não encontrado!"));
		
		entity.setEndereco(p.getEndereco());
		entity.setFirstName(p.getFirstName());
		entity.setGender(p.getGender());
		entity.setLastName(p.getLastName());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void deleteById(String id) {
		logger.info("---------------------------------------------------------");
		logger.info("Método remover PersonVO - ID:"+id);
		logger.info("---------------------------------------------------------");

		var PersonVO = repository.findById(Long.valueOf(id)).orElseThrow(
				() -> new ResourceNotFoundException("Não encontrado!"));
		
		repository.delete(PersonVO);
	}
	
	public PersonVO findById(String id) {
		logger.info("---------------------------------------------------------");
		logger.info("Método buscar PersonVO - ID:"+id);
		logger.info("---------------------------------------------------------");
		
		
		return DozerMapper.parseObject(repository.findById(Long.valueOf(id)).orElseThrow(
				() -> new ResourceNotFoundException("Não encontrado!")), PersonVO.class);
	}

	
	public List<PersonVO> findAll(){
		
		logger.info("---------------------------------------------------------");
		logger.info("Método buscar Todos");
		logger.info("---------------------------------------------------------");

		return  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class); 
		
	}
}
