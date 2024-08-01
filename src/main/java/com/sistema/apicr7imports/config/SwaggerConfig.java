package com.sistema.apicr7imports.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.domain.Product;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	LinkDiscoverers discoverers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

	}

	@Bean
	Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sistema.apicr7imports")).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false).apiInfo(apiInfo()).ignoredParameterTypes(Category.class)
				.ignoredParameterTypes(Brand.class)
				.ignoredParameterTypes(Product.class);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"API Rest CR7 Imports", "", "v1", "", new Contact("Douglas Proença",
						"https://www.linkedin.com/in/douglas-proen%C3%A7a/", "douglasp.r.desouza@gmail.com"),
				"License of API", "License of URL", Collections.emptyList());
	}
}