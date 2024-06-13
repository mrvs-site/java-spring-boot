package br.com.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.services.UserServices;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	public UserServices service;
	


	@GetMapping("{userName}")
	public UserDetails getUser(@RequestParam String userName) {
		return service.loadUserByUsername(userName);
	}
	
}
