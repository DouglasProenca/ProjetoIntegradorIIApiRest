package com.sistema.apicr7imports.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.config.SecurityJwtConfigProperties;
import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
	
	private final SecurityJwtConfigProperties securityJwtConfigProperties;
	private final UserDetailsService userDetailsService;
	
   @Autowired
   RedisTemplate<String, String> redisTemplate;
	
	//@PostConstruct
	//protected void init() {
	//	securityJwtConfigProperties.setSecret_key(Base64.getEncoder().encodeToString(securityJwtConfigProperties.getSecret_key().getBytes()));
	//}
	
	public String createToken(String username, List<String> roles) {
		
		String token = redisTemplate.opsForValue().get(username);
		if(token == null) {
		
			Claims claims = Jwts.claims().setSubject(username);
			claims.put("roles", roles);
		
			Date now = new Date();
			Date validity = new Date(now.getTime() + Long.valueOf(securityJwtConfigProperties.getExpire_length()));

			token = Jwts.builder()
					.setSubject(username)
					.setClaims(claims)
					.setIssuedAt(now)
					.setExpiration(validity)
					.signWith(SignatureAlgorithm.HS256, securityJwtConfigProperties.getSecret_key())
					.compact();
		
			redisTemplate.opsForValue().set(username, token, 1, TimeUnit.HOURS);
		
			return token;
		}
		
		return token;
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(securityJwtConfigProperties.getSecret_key()).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) 
			return bearerToken.substring(7, bearerToken.length());
				
		return null;
	}
	
	public Boolean validateToken(String token) {
		try {
			if (Jwts.parser().setSigningKey(securityJwtConfigProperties.getSecret_key()).parseClaimsJws(token).getBody().getExpiration().before(new Date())) 
				throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
				
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

}