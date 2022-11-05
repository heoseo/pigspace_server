package com.pigspace.common.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(title = "PigSpace API",
		        description = "쩝쩝박사 API 명세서입니다.\n\n <a href='https://www.notion.so/08ec5510a4054318b0bee5fb40fd28bb?v=10ed6d2ef48c44bb8995eac39934271e'>인터페이스정의서 바로가기</a>\n ",
		        version = "v1"))
@Configuration
public class Swagger2Config {

    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder()
                .group("01.Member")
                .pathsToMatch("/member/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("02.Board")
                .pathsToMatch("/board/**")
                .build();
    }

}