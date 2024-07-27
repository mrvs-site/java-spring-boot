package br.com.projeto.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.projeto.security.jwt.JwtTokenFilter;
import br.com.projeto.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	PasswordEncoder passwordEncoder() {

		Map<String, PasswordEncoder> encoders = new HashMap<>();
		Pbkdf2PasswordEncoder pbkd = new Pbkdf2PasswordEncoder("", 8, 185000,
				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkd);
		DelegatingPasswordEncoder passowordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passowordEncoder.setDefaultPasswordEncoderForMatches(pbkd);

		return passowordEncoder;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);

		return http
				.httpBasic(basic -> basic.disable())
				.csrf(csrf -> csrf.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auto -> auto
						.requestMatchers("/auth/signin/v1/**", "/auth/refresh/v1/**", "/v3/api-docs/**", "/swagger-ui/**")
						.permitAll())
				.authorizeHttpRequests(auto -> auto.requestMatchers("/api/**").authenticated())
				.authorizeHttpRequests(auto -> auto.requestMatchers("/users").denyAll())
				.cors(cors -> {})
				.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
