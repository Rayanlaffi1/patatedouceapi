package dev.rlaffi.spring.patatedouce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EntityScan("dev.rlaffi.spring.patatedouce.entities")
@EnableJpaRepositories("dev.rlaffi.spring.patatedouce.repositories")
public class StartSpringApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StartSpringApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StartSpringApp.class);
	}
	
}
