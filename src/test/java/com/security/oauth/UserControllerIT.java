package com.security.oauth;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TokenStore tokenStore;
    
 
    @MockBean
	private ConsumerTokenServices consumerTokenServices;

    @Before
    public void setUp() {

        final OAuth2AccessToken token = new DefaultOAuth2AccessToken("FOO");
        final ClientDetails client = new BaseClientDetails("client", null, "read", "client_credentials", "ROLE_CLIENT");
        final OAuth2Authentication authentication = new OAuth2Authentication(
                new TokenRequest(null, "client", null, "client_credentials").createOAuth2Request(client), null);

        tokenStore.storeAccessToken(token, authentication);

    }

    @Test
    public void testGivenPathUsersWhenGettingForEntityThenStatusCodeIsOk() {

        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer FOO");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Given Path Users
        final UriComponentsBuilder uri = UriComponentsBuilder.fromPath("/api/users");

        // When Getting For Entity
        final ResponseEntity<String> response = testRestTemplate.exchange(uri.build().toUri(), HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        // Then Status Code Is Ok
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

}