package br.com.projeto.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.projeto.data.vo.v2.PersonVOV2;
import br.com.projeto.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEtityToVo(Person p) {
		
		PersonVOV2 vo = new PersonVOV2();
		vo.setBith(new Date());
		vo.setEndereco(p.getEndereco());
		vo.setFirstName(p.getFirstName());
		vo.setGender(p.getGender());
		vo.setLastName(p.getLastName());
		vo.setId(p.getId());
		
		return vo;
	}

	public Person convertVoToEtity(PersonVOV2 p) {
		
		Person entity = new Person();
		entity.setEndereco(p.getEndereco());
		entity.setFirstName(p.getFirstName());
		entity.setGender(p.getGender());
		entity.setLastName(p.getLastName());
		entity.setId(p.getId());
		
		return entity;
	}
	
	
}
