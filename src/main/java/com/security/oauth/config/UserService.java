package com.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.oauth.entities.User;
import com.security.oauth.repositories.UserRepository;

@Service("userDetailsService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try{
			user = userRepository.findByUsername(username);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new CustomUserDetails(user);
	}
}