package com.sistema.apicr7imports.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "mail.config")
public class MailConfigProperties {

	Integer port;
	String host;
	Transport transport;
	Map<String, Object> properties;
	Boolean debug;

	@Data
	public static class Transport {
		String protocol;
	}
}
