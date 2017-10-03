package com.security.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import com.security.oauth.config.OAuthAuthenticationProvider;


@SpringBootApplication
public class SprintBootOAuthDemoApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(SprintBootOAuthDemoApplication.class, args);
	}

	@Autowired
	private OAuthAuthenticationProvider oAuthAuthenticationProvider;

	@Autowired
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Arrays.asList(oAuthAuthenticationProvider));
		
	}	
}