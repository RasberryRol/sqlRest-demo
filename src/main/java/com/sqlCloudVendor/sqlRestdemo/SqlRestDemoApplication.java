package com.sqlCloudVendor.sqlRestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class SqlRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqlRestDemoApplication.class, args);
	}

	//Responsible to customize swagger documentation
	@Bean
	public Docket swaggerConfiguration(){
		return  new Docket(DocumentationType.SWAGGER_2).select()
				.paths(PathSelectors.ant("/cloudvendor/*"))
				.apis(RequestHandlerSelectors.basePackage("com.sqlCloudVendor.sqlRestdemo"))
				.build()
				.apiInfo(apiCustomData());
	}

	private ApiInfo apiCustomData(){
		return new ApiInfo(
				"Cloud Vendor API Application",
				"Cloud Vendor Documentation",
				"1.0",
				"Cloud Vendor Service Terms",
				new Contact("Dief Gerard","https://github.com/RasberryRol/diefAdemy",
						"dief9erard@gmail.com"),
				"Think Constructive License",
				"https://github.com/RasberryRol/diefAdemy",
				Collections.emptyList()
		);
	}

}
