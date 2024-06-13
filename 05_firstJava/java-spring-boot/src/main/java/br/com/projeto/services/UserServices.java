package br.com.projeto.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.repositories.UserRepository;

@Service
public class UserServices implements UserDetailsService{

	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@Autowired
	private UserRepository repository;
	
	

	public UserServices(UserRepository repository) {
		this.repository = repository;
	}




	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("get UserName"+" --- "+userName);
		
		var user = repository.findByUserName(userName);
		
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("Usuário não encontrado -- "+userName);
		}
	}
	
	
	
}
