package com.mfh.oa31authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfiguration {

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.antMatcher("/api/v1/*")
        .authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/login")
            .permitAll()
            .anyRequest()
            .authenticated())
        .formLogin(Customizer.withDefaults());

    return http.build();
  }
}
