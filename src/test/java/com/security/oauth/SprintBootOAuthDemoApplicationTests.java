package com.security.oauth;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.security.oauth.config.OAuthAuthenticationProvider;
import com.security.oauth.config.UserPasswordConverter;
import com.security.oauth.controllers.SecureService;
import com.security.oauth.repositories.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(SecureService.class)
public class SprintBootOAuthDemoApplicationTests {
	
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private OAuthAuthenticationProvider provider;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private ConsumerTokenServices consumerTokenServices;
	
	@MockBean
	private UserPasswordConverter userPasswordConverter;
	
	@MockBean
	private JdbcTokenStore tokenStore;
	
	
	

	
	
	@Test
	public void getAccessToken()
	  throws Exception {
	     
	    
	   /* mvc.perform(post("/oauth/token")
	      .contentType(MediaType.APPLICATION_JSON)).h
	      .andExpect(status().isOk());*/
	      //.andExpect(jsonPath("$[0].name", is(alex.getName())));
	    
	    /*
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "registeredclient");
        params.add("username", "test.test@it.com");
        params.add("password", "WatersVmlit201&");
     
        ResultActions result 
          = mvc.perform(post("/oauth/token")
            .params(params)
           // .with(new RehttpBasic("fooClientIdPassword","secret"))
            .accept("application/x-www-form-urlencoded"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
     
        String resultString = result.andReturn().getResponse().getContentAsString();
     
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        System.out.println( jsonParser.parseMap(resultString).get("access_token").toString());*/
		
		mvc.perform(post("/one")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
			      //.andExpect(jsonPath("$[0].name", is(alex.getName())));
	}

	
}
