package com.security.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.oauth.config.CustomUserDetails;
import com.security.oauth.entities.Role;
import com.security.oauth.entities.User;
import com.security.oauth.repositories.UserRepository;
import com.security.oauth.services.UserService;

@SpringBootApplication
public class SprintBootOAuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintBootOAuthDemoApplication.class, args);
	}
	
	
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	

	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService service) throws Exception {
		//Setup a default user if db is empty
		if (repository.count()==0)
			service.save(new User("user", "pass", Arrays.asList(new Role("USER"), new Role("SUPERUSER"))));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
	
	
}
