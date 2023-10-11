package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {

	//Filter chain
	// authenticate all requests
	//basic authentication
	//disabling csrf
	//stateless rest api
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//1: Response to preflight request doesn't pass access control check
		//2: basic auth
		return
				http
					.authorizeHttpRequests(
						auth ->
							auth
									.requestMatchers( "/api/**").permitAll()
									.anyRequest().authenticated()
									.requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
									.anyRequest().authenticated()
									.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
									.anyRequest().authenticated()

						)
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(
						session -> session.sessionCreationPolicy
						(SessionCreationPolicy.STATELESS))
						// .csrf().disable() Deprecated in SB 3.1.x
					.csrf(csrf -> csrf.disable()) // Starting from SB 3.1.x using Lambda DSL
			     // .csrf(AbstractHttpConfigurer::disable) // Starting from SB 3.1.x using Method Reference
					.build();
	}

}
