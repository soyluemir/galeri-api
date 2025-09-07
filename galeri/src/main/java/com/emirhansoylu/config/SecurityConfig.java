package com.emirhansoylu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.emirhansoylu.handler.AuthEntryPoint;
import com.emirhansoylu.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	public static final String REGISTER = "/register";
	public static final String AUTHENTICATE ="/authenticate";
	public static final String REFRESH_TOKEN = "/refreshtoken";
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests(request->
		request.requestMatchers( REGISTER , AUTHENTICATE , REFRESH_TOKEN).permitAll() //bu url adresine izin ver
		.anyRequest()
		.authenticated()) //bunun dışındakileri  yetki kontrolü yap kitle
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and() //artık tokeni kullandığımızda token süresi bitmişse 401
		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
		
	}

}
