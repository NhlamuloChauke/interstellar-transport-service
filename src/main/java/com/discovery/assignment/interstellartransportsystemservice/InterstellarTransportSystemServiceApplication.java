package com.discovery.assignment.interstellartransportsystemservice;

import io.swagger.models.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author nhlamulo
 */
@SpringBootApplication @ComponentScan(basePackages = {"com.discovery.assignment.interstellartransportsystemservice.repositories",
                               "com.discovery.assignment.interstellartransportsystemservice.controller"})
public class InterstellarTransportSystemServiceApplication extends WebMvcConfigurationSupport {
/*
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("zcom.discovery.assignment.interstellartransportsystemservice.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData())
				.tags(new Tag("BucketClassification", "Bucket classification static data service"));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("IBDS Stream-Validation-Service REST API")
				.description("\"IBDS Stream-Validation-Service REST API description\"")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}*/

	public static void main(String[] args) {
		SpringApplication.run(InterstellarTransportSystemServiceApplication.class, args);
	}

}
