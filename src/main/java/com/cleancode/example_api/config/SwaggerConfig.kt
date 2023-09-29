package com.cleancode.example_api.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

//http://localhost:8080/swagger-ui/index.html
//TODO [js] swagger info 및 설정
/**
 * swagger 설정
 * @author js
 */
@OpenAPIDefinition(
    info = Info(title = "API 명세서",
        description = "Batch Service API 명세서",
        version = "0.1.1",
        )
)
@Configuration
public open class SwaggerConfig {
}