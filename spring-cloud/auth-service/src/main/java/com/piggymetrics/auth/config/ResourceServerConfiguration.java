package com.piggymetrics.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
         // permit urls using for login page
		.antMatchers("/actuator/**").permitAll()
		.anyRequest()
		.authenticated();
	}
	 

}
