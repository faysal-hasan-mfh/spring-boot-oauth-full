package com.mfh.oa31resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfiguration {

//  @Value("${jwk.set.uri}")
//  private String issuerUri;
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.antMatcher("/api/v1/**")
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    return http.build();
  }

//  @Bean
//  public JwtDecoder jwtDecoder() {
//    return JwtDecoders.fromIssuerLocation(issuerUri);
//  }

}
