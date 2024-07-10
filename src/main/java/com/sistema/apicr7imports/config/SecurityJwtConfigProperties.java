package com.sistema.apicr7imports.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt.token")
public class SecurityJwtConfigProperties {

	private Integer expire_length;
	private String secret_key;
	/**
	 * @return the expire_length
	 */
	public Integer getExpire_length() {
		return expire_length;
	}
	/**
	 * @param expire_length the expire_length to set
	 */
	public void setExpire_length(Integer expire_length) {
		this.expire_length = expire_length;
	}
	/**
	 * @return the secret_key
	 */
	public String getSecret_key() {
		return secret_key;
	}
	/**
	 * @param secret_key the secret_key to set
	 */
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}
	
}
