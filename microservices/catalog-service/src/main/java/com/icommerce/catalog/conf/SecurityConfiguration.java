package com.icommerce.catalog.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
	String clientId;

	@Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
	String clientSecret;

	@Value("${spring.security.oauth2.resourceserver.opaque-token.introspection-uri}")
	String introspectionUri;

	@Bean
	public OpaqueTokenIntrospector introspector() {
		return new ICAuthoritiesOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret);
	}

	@Bean
	public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
		
		http.cors();
		
		http.authorizeHttpRequests(
				authorize -> authorize
                .requestMatchers("/" , "/app-message").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(opaqueToken -> opaqueToken.introspector(
						new ICAuthoritiesOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret))));
		return http.build();
	}

}
