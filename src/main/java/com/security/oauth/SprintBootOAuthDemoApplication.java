package com.security.oauth;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.oauth.config.CustomUserDetails;
import com.security.oauth.entities.User;
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
		
		
		builder.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				User user = repository.findByUsername(username);
				
				if (user == null) {
		            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		        }
				
				return new CustomUserDetails(user);
			}
		}).passwordEncoder(passwordEncoder);
		
		
		
		//builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
		String s = passwordEncoder.encode("shirehplc");
		String b = Base64.encodeBase64String(s.getBytes());
		System.out.println("encodedpassword   "+ b);
		
	}
	
}
