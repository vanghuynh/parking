package com.wego.api.parking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                            
          .paths(PathSelectors.any())         
          .apis(RequestHandlerSelectors.basePackage("com.wego.api.parking.controller"))
          .build();                                           
    }
    //visit http://localhost:8080/swagger-ui.html
    // Work around for working with spring boot 2.6
    // https://github.com/springfox/springfox/issues/3462
}
