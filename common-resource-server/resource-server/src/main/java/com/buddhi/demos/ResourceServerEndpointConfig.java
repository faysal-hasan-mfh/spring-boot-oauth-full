package com.buddhi.demos;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerEndpointConfig extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(HttpSecurity http) throws Exception {
    //            http.authorizeRequests().antMatchers("/account").hasAuthority("ROLE_A");

    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/**")
        .authenticated()
        .antMatchers("/account")
        .hasAuthority("ROLE_A")
        .anyRequest()
        .authenticated();

//    http.cors()
//        .and()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .antMatcher("/api/**")
//        .authorizeRequests()
//        .antMatchers("/api/signin**")
//        .permitAll()
//        .antMatchers("/api/signin/**")
//        .permitAll()
//        .antMatchers("/api/glee**")
//        .hasAnyAuthority("ADMIN", "USER")
//        .antMatchers("/api/users**")
//        .hasAuthority("ADMIN")
//        .antMatchers("/api/**")
//        .authenticated()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .exceptionHandling()
//        .authenticationEntryPoint(customAuthenticationEntryPoint)
//        .accessDeniedHandler(new CustomAccessDeniedHandler());

    //    http.cors()
    //        .and()
    //        .sessionManagement()
    //        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //        .and()
    //        .antMatcher("/api/**")
    //        .authorizeRequests()
    //        .antMatchers("/account")
    //        .hasAuthority("ROLE_A")
    //        .antMatchers("/api/signin**")
    //        .permitAll()
    //        .antMatchers("/api/signin/**")
    //        .permitAll()
    //        .antMatchers("/api/glee**")
    //        .hasAnyAuthority("ADMIN", "USER")
    //        .antMatchers("/api/users**")
    //        .hasAuthority("ADMIN")
    //        .antMatchers("/api/**")
    //        .authenticated()
    //        .anyRequest()
    //        .authenticated();
  }
}
