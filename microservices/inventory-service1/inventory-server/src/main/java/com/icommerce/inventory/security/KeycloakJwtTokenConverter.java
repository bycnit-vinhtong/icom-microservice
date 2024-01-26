package com.icommerce.inventory.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeycloakJwtTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {

    private static final String RESOURCE_ACCESS = "resource_access";
    private static final String ROLES = "roles";
    private static final String ROLE_PREFIX = "";
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;
    private final TokenConverterProperties properties;

    public KeycloakJwtTokenConverter(
            JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter,
            TokenConverterProperties properties) {
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
        this.properties = properties;
    }

    @Override
    public JwtAuthenticationToken convert(@NonNull Jwt jwt) {
        /**
         * Converts the JWT token's resource access claims into a stream of SimpleGrantedAuthority objects.
         * Each SimpleGrantedAuthority represents a role associated with the resource.
         */
        Map<String, Object> claimMapObject = jwt.getClaimAsMap(RESOURCE_ACCESS);
        Map<String, Object> resourceMapDataObject = (Map<String, Object>) claimMapObject.get(properties.getResourceId());
        Collection<String> roles = (Collection<String>) resourceMapDataObject.get(ROLES);
        Stream<SimpleGrantedAuthority>  accesses = roles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role)).distinct();

        Set<GrantedAuthority> authorities = accesses
                .collect(Collectors.toSet());


        String principalClaimName = properties.getPrincipalAttribute()
                .map(jwt::getClaimAsString)
                .orElse(jwt.getClaimAsString(JwtClaimNames.SUB));

        return new JwtAuthenticationToken(jwt, authorities, principalClaimName);
    }
}