package com.security.oauth.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.oauth.ResourceResponse;
import com.security.oauth.entities.User;
import com.security.oauth.repositories.UserRepository;

@RestController
public class SecureService {

	@Autowired
	private UserRepository repository;

	@GetMapping(value="/getUserDetails")
	public ResourceResponse retrieveUserInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ResourceResponse res = new ResourceResponse();
		User user =repository.findByUsername(authentication.getName());
		res.setUsername(authentication.getName());
		res.setFirstname(user.getFirstname());
		res.setLastname(user.getLastname());
		res.setEmail(user.getEmail());
		res.setUserId(user.getEmail());
		return res;
	}

	@Autowired
	private TokenStore tokenStore;

	@GetMapping(value="/logout")
	public void logout(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			String tokenValue = authHeader.replace("Bearer", "").trim();
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
			tokenStore.removeAccessToken(accessToken);
		}
	}

}
