package com.security.oauth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.security.oauth.entities.EncodeDetails;
import com.security.oauth.entities.User;
import com.security.oauth.repositories.EncodeRepository;
import com.security.oauth.repositories.UserRepository;
import com.security.oauth.util.CustomPasswordEncoder;

/**
 * Used for checking the token from the request and supply the UserDetails if the token is valid

 */
@Component
public class OAuthAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


	@Autowired
	private CustomPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	@Autowired
	private EncodeRepository encodeRepository;



	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		String password = authentication.getCredentials().toString();
		User user = null;
		try{
			user = repository.findByUsername(username);

			EncodeDetails encodeDetails = encodeRepository.findByUserName(user.getUsername());
			boolean pwdMatches  =false;
			System.out.println("************ " +encodeDetails.getName());
			
			if(null != user){
				pwdMatches = passwordEncoder.pwdMatches(password, user.getPassword(), encodeDetails.getName(), encodeDetails.getIterations(), encodeDetails.getKeyLength());
			}


			if(!pwdMatches){
				throw new UsernameNotFoundException(String.format("Password is not correct", username));
			}

		} catch (Exception ex) {
			throw new AuthenticationServiceException("user credentials are wrong", ex);
		}

		return new CustomUserDetails(user);
	}

}
