package dev.rlaffi.spring.patatedouce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI customOpenAPI() {
		final String securitySchemeName = "bearer auth";

		License license = new License().name("Apache 2.0").url("https://springdoc.org");
		Info info = new Info().title("Spring PATATE DOUCE REST API").version("v0.0.1").license(license);
		return new OpenAPI().info(info)
				.addSecurityItem(
						new SecurityRequirement()
								.addList(securitySchemeName))
				.components(
						new Components()
								.addSecuritySchemes(
										securitySchemeName,
										new SecurityScheme()
												.name(securitySchemeName)
												.type(SecurityScheme.Type.HTTP)
												.scheme("bearer")
												.bearerFormat("JWT")));
	}

	@Bean
	GroupedOpenApi completeApi() {
		return GroupedOpenApi.builder().group("Complete").pathsToMatch("/**").build();
	}

}
