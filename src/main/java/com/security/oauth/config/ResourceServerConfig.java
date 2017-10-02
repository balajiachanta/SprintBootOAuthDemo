package com.security.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	 private static final String RESOURCE_ID = "oauth2-resource";
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/getUserDetails/**").authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and().logout().logoutUrl("/oauth/logout");
                
        
    }

   
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

}
