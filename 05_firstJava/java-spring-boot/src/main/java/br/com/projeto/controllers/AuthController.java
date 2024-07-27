package br.com.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.data.vo.v1.security.AccountCredentialsVO;
import br.com.projeto.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint", description = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthServices authServices;

	@SuppressWarnings("rawtypes")
	@Operation(summary = "Autenticação")
	@PostMapping(value = "/signin/v1")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {

		if (verificarNuloVazio(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("credenciais nulas!!!");
		
		var token = authServices.signin(data);
		
		if(token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("credenciais inválidas!!!");
		}else {
			return token;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Refresh Token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String userName, @RequestHeader("Authorization") String refreshToken) {
		
		if (verificarTokenVazio(userName, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("credenciais inválidas!!!");
		
		var tokenNovo = authServices.refreshToken(userName, refreshToken);
		
		if(tokenNovo == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("credenciais inválidas!!!");
		}else {
			return tokenNovo;
		}
	}

	private boolean verificarTokenVazio(String userName, String refreshToken) {
		return userName == null || userName.isBlank() || refreshToken ==null || refreshToken.isBlank();
	}

	private boolean verificarNuloVazio(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null
				|| data.getPassword().isBlank();
	}

}
