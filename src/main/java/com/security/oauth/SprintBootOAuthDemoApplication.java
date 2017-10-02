package com.security.oauth;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.security.oauth.config.CustomUserDetails;
import com.security.oauth.repositories.UserRepository;
import com.security.oauth.services.UserServiceDAO;
import com.security.oauth.util.CustomPasswordEncoder;

@SpringBootApplication
public class SprintBootOAuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintBootOAuthDemoApplication.class, args);
	}

	
	@Autowired
	private CustomPasswordEncoder passwordEncoder;

	

	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserServiceDAO service) throws Exception {
		
		
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
		String s = passwordEncoder.encode("shirehplc");
		String b = Base64.encodeBase64String(s.getBytes());
		System.out.println("encodedpassword   "+ b);
		
	}

	
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
	
	
}
