package com.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.security.oauth.config.CustomUserDetails;
import com.security.oauth.repositories.UserRepository;
import com.security.oauth.services.UserServiceDAO;


@SpringBootApplication
public class SprintBootOAuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintBootOAuthDemoApplication.class, args);
	}

	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserServiceDAO service) throws Exception {
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}
	
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
	
	
}
