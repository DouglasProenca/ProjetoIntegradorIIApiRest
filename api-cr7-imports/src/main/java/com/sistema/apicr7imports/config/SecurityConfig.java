package com.sistema.apicr7imports.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sistema.apicr7imports.security.jwt.JwtConfigurer;
import com.sistema.apicr7imports.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	 @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	                .httpBasic().disable()
	                .csrf(AbstractHttpConfigurer::disable)
	                .sessionManagement(
	            		session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authorizeHttpRequests(
	                    authorizeHttpRequests -> authorizeHttpRequests
	                        .antMatchers("/acesso/login", "/api-docs/**", "/swagger-ui/**").permitAll()
	                        .antMatchers("/apicr7imports/private/**").authenticated().antMatchers("/apicr7imports/users").denyAll()
	                )
	                .cors()
	                .and()
	                .apply(new JwtConfigurer(tokenProvider))
	                .and()
	                 .build();
	 
	    }

}