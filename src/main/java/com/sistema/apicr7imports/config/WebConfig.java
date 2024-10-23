package com.sistema.apicr7imports.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer ) {
		configurer.favorPathExtension(false)
		.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON
				          , MediaType.APPLICATION_OCTET_STREAM
				          , MediaType.APPLICATION_PDF
				          , MediaType.MULTIPART_FORM_DATA
				          , MediaType.IMAGE_PNG);
	}

}