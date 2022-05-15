package com.philips.informationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration class that is necessary for Swagger Api
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Bean Definition of Swagger Api
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.philips.informationservice"))
                .paths(PathSelectors.regex("/api/v1.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * @return Api information of the Api that has been created
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Information Service API")
                .description("Documentation of Information API")
                .contact(new Contact("Zeynep Dogus", "https://github.com/zeynepdogus/", "doguszeynep@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.0.1")
                .build();
    }
}
