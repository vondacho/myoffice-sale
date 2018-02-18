package edu.noia.myoffice.sale.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SpringfoxConfiguration {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("myOffice API")
                .apiInfo(apiInfo())
                .select()
                .paths(salePaths())
                .build();
    }

    private Predicate<String> salePaths() {
        return regex("/api/sale.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("myOffice Sale API")
                .description("The Sale API is a part of the myOffice API and provides endpoints for Cart entity management and sale event stream consumption.")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0")
                .build();
    }
}
