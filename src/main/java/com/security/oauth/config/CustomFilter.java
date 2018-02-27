package com.security.oauth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.filter.GenericFilterBean;



//@Component
public class CustomFilter extends GenericFilterBean{

	 @Autowired
	    private DataSource dataSource;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if(request instanceof HttpServletRequest) {
			AccessTokenRequest atr = new DefaultAccessTokenRequest();
			System.out.println("access token is "+atr.getExistingToken());
			if(null != atr.getExistingToken()){
				System.out.println("refresh token is "+atr.getExistingToken().getRefreshToken());
			}
			
			JdbcTokenStore js = new JdbcTokenStore(dataSource);
			System.out.println("we are in");
		}else {
			System.out.println("we are out");
		}
		
		
		
		
		chain.doFilter(request, response);


	}

}
