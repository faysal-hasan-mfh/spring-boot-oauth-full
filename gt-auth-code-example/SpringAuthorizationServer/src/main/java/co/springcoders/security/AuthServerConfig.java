package co.springcoders.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import co.springcoders.security.jwt.JwtTokenEnhancer;

@SuppressWarnings("deprecation")
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	 @Bean("passwordEncoder")
	 BCryptPasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		 clients
		 	.inMemory()
		 		.withClient("a")
		 			.secret(passwordEncoder().encode("a"))
		 			.scopes("read", "write")
		 			.authorizedGrantTypes("authorization_code", "refresh_token")
		 			.redirectUris("http://localhost:8090/showEmployees")
		 			.autoApprove(true);
	 }
	
	@Bean
	JwtTokenStore getAccessTokenConverter() {
		return new JwtTokenStore(JwtTokenEnhancer.getInstance());
	}
}
