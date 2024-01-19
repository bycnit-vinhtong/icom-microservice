package com.icommerce.catalog.conf;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import lombok.extern.log4j.Log4j2;

/**
 * Contains necessary configurations regarding authentication to auth-service
 */
@Log4j2
@Configuration
public class CustomRestTemplate {
    @Bean
    public RequestInterceptor icomAuthInterceptor() {
        return requestTemplate -> {
            var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            final var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            requestTemplate.header(HttpHeaders.AUTHORIZATION, authorizationHeader);
            log.debug("--- request = " + requestTemplate);

        };
    }
}