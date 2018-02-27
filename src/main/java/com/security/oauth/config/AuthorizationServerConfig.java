package com.security.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;
    
    @Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
    
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	//endpoints.pathMapping("/oauth/token", "/water/token");
    	endpoints.tokenEnhancer(customTokenEnhancer);
    	endpoints.authenticationManager(authenticationManager);
    	endpoints.tokenStore(tokenStore());
    	endpoints.userDetailsService(userDetailsService);
    }

  
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	
    	clients.jdbc(dataSource);
    }

   
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    	security.checkTokenAccess("permitAll()");
        security.tokenKeyAccess("permitAll()");
    }


}
