package com.digicert.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {

        Contact contact = new Contact("Muhammed Mashiur Rahman", "", "oncmmr@gmail.com");
        Collection<VendorExtension> vendorExtensions = new ArrayList<>();

        ApiInfo apiInfo = new ApiInfo("User API",
                "User API for DigiCert",
                "1.0.0", "",
                contact,
                "",
                "",
                vendorExtensions);

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.digicert.usermanagement"))
                .paths(PathSelectors.any())
                .build();
    }
}
