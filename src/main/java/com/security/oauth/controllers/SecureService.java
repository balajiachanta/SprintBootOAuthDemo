package com.security.oauth.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.oauth.ResourceResponse;

@RestController
public class SecureService {

	@GetMapping(value="/getUserDetails")
	public ResourceResponse retrieveUserInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ResourceResponse res = new ResourceResponse();
		res.setUsername(authentication.getName());
		return res;
	}
	
}
