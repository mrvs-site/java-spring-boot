package br.com.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.data.vo.v1.security.AccountCredentialsVO;
import br.com.projeto.data.vo.v1.security.TokenVO;
import br.com.projeto.repositories.UserRepository;
import br.com.projeto.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository repository;

	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsVO data) {
		try {
			var userName = data.getUserName();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

			var user = repository.findByUserName(userName);
			var tokenResponse = new TokenVO();
			
			if(user != null) {
				tokenResponse = jwtTokenProvider.createAccessToken(userName, user.getRoles());
			}else {
				throw new UsernameNotFoundException("Usuário não encontrado !!! "+userName);
			}
			
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new BadCredentialsException("usuário inválido!!!");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String userName, String refreshToken) {
			
			var user = repository.findByUserName(userName);
			var tokenResponse = new TokenVO();
			
			if(user != null) {
				tokenResponse = jwtTokenProvider.createRefreshToken(refreshToken);
			}else {
				throw new UsernameNotFoundException("Usuário não encontrado !!! "+userName);
			}
			
			return ResponseEntity.ok(tokenResponse);
	}
}
