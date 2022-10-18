package com.start.testes.kt.library.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.RequestHandler
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerUiConfig {

    @Bean
    fun gettingApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData())
            .useDefaultResponseMessages(false)
    }

    private fun metaData(): ApiInfo {
        return ApiInfoBuilder()
            .title("LIBRARY API")
            .description("API to Library management.")
            .version("0.0.1")
            .contact(
                 Contact(
                     "Wendel Santos",
                     "https://github.com/ricksantos88",
                    "henrique.santos1988@gmail.com"))
            .build()
    }

}