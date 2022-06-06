package com.example.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
  @Autowired
  public PasswordEncoder passwordEncoder;
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
				.inMemory()
				.withClient("a")
				.secret(passwordEncoder.encode("a"))
				.authorities("ROLE_A","ROLE_B","ROLE_TRUSTED_CLIENT")
				.scopes("all")
				.authorizedGrantTypes("password")
				.and()
				.withClient("b")
				.secret(passwordEncoder.encode("b"))
				.authorities("ROLE_C")
				.scopes("all")
				.authorizedGrantTypes("password");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()");
		// this means if you try to check token is valid or not using the following url
    // http://localhost:8100/oauth/check_token?token=9d1101a8-c50d-4b16-a380-0d95ae68cfa7
    // token is 9d1101a8-c50d-4b16-a380-0d95ae68cfa7
    // no authorization is required

    // but if you add has authority like following then Authorization user should have this role
//		security.checkTokenAccess("hasAuthority('ROLE_C')");
	}

  private AuthenticationManager authenticationManager;

  @Autowired
  public OAuth2Config(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.authenticationManager(authenticationManager);
  }

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}


}
