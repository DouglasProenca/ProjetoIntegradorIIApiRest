package com.sistema.apicr7imports.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	LinkDiscoverers discoverers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

	}

	@Bean
	OpenAPI apiInfo() {
		return new OpenAPI()
				     .info(new Info()
	                    .title("API Rest CR7 Imports")
	                    .version("1.0")
	                    .description("API para consultas CR7Imports")
	                    .contact(new Contact()
	                        .name("Douglas Proença")
	                        .email("douglasp.r.desouza@gmail.com")
	                        .url("https://www.linkedin.com/in/douglas-proen%C3%A7a/")))
				     .components(new Components()
				    		 .addSecuritySchemes("Token de autorização", new SecurityScheme()
				    				                                      .type(SecurityScheme.Type.HTTP)
				    				                                      .scheme("Bearer")
				    				                                      .bearerFormat("JWT")));
				          
	}
	
	@Bean
	OpenApiCustomiser sortSchemasAlphabetically() {
	    return openApi -> {
	        //Map<String, Schema> schemas = openApi.getComponents().getSchemas();
	        openApi.getComponents().setSchemas(new TreeMap<>(openApi.getComponents().getSchemas()));
	    };
	}
	
}