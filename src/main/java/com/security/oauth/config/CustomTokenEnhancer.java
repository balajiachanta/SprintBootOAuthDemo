package com.security.oauth.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	HttpServletResponse httpres;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
			OAuth2Authentication authentication) {
	
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("username", user.getUsername());
        
        System.out.println("access token is " +((DefaultOAuth2AccessToken) accessToken).getValue());
        
        System.out.println(httpres);
       
        Cookie accesstokencookie = new Cookie("accesstoken", ((DefaultOAuth2AccessToken) accessToken).getValue());

        accesstokencookie.setHttpOnly(true);
        accesstokencookie.setMaxAge(1800);
        accesstokencookie.setSecure(true);
        accesstokencookie.setPath("/");
        accesstokencookie.setDomain("test");

        httpres.addCookie(accesstokencookie);
       
        Cookie refreshtokencookie = new Cookie("refreshtoken", ((DefaultOAuth2AccessToken) accessToken).getRefreshToken().getValue());

        refreshtokencookie.setHttpOnly(true);
        refreshtokencookie.setMaxAge(1800);
        refreshtokencookie.setSecure(true);
        refreshtokencookie.setPath("/");
        refreshtokencookie.setDomain("test");

        httpres.addCookie(refreshtokencookie);


        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
	}

}