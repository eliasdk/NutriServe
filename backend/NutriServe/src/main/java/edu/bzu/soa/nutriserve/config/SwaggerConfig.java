package edu.bzu.soa.nutriserve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.bzu.soa.nutriserve.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
    

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "<b> NutriServe </b>   REST API",
                " REST API for Online Nitrition Service",
                "1.0",
                "Terms of service",
                new Contact("Ahmad, Ali && Elias", "https://facebook.com", "aae@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
