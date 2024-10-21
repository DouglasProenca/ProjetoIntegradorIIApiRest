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

import static org.springframework.security.config.Customizer.withDefaults;
import com.sistema.apicr7imports.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	 @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
                    .httpBasic(basic -> basic.disable())
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(
                            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(
                            authorizeHttpRequests -> authorizeHttpRequests
                                    .antMatchers("/acesso/login", "/api-docs/**", "/swagger-ui/**").permitAll()
                                    .antMatchers("/apicr7imports/private/**").authenticated().antMatchers("/apicr7imports/users/blocked").denyAll()
                    )
                    .cors(withDefaults())
                    .apply(new JwtConfigurer(tokenProvider))
                    .and()
                    .build();
	 
	    }

}