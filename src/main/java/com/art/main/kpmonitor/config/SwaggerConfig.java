package com.art.main.kpmonitor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI 설정 클래스
 * API 문서화를 위한 설정을 관리합니다.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("김치 프리미엄 모니터링 API")
                        .description("김프 알림 서비스를 위한 REST API")
                        .version("1.0.0"));
    }
}