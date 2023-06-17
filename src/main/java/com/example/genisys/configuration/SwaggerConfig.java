package com.example.genisys.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI productOpenAPI(ProductInfo productInfo){
        return new OpenAPI()
                .info(new Info().title(productInfo.getTitle())
                .description(productInfo.getDescription())
                .version(productInfo.getVersion())
                .contact(new Contact().email(productInfo.getContactEmail()).name(productInfo.getContactName()).url(productInfo.getContactUrl()))
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LIcense-2.0.txt")));

    }
}
