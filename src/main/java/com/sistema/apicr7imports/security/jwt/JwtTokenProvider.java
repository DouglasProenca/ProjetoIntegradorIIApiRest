package com.sistema.apicr7imports.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret_key}")
	String secretKey;
	
	@Value("${security.jwt.token.expire_length}")
	String validityInMilliseconds;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	//@Autowired
   // RedisTemplate<String, String> redisTemplate;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String username, List<String> roles) {
		
		//String token = redisTemplate.opsForValue().get(username);
		String token = null;
		if(token == null) {
		
			Claims claims = Jwts.claims().setSubject(username);
			claims.put("roles", roles);
		
			Date now = new Date();
			Date validity = new Date(now.getTime() + Long.valueOf(validityInMilliseconds));

			token = Jwts.builder()
					.setSubject(username)
					.setClaims(claims)
					.setIssuedAt(now)
					.setExpiration(validity)
					.signWith(SignatureAlgorithm.HS256, secretKey)
					.compact();
		
			//redisTemplate.opsForValue().set(username, token, 1, TimeUnit.HOURS);
		
			return token;
		}
		
		return token;
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) 
			return bearerToken.substring(7, bearerToken.length());
				
		return null;
	}
	
	public Boolean validateToken(String token) {
		try {
			if (Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date())) 
				throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
				
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

}