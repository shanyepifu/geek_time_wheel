package com.geek.wheel.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @DESCRIPTION: SwaggerUI配置
 * @CLASSNAME: SwaggerUIConfig
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019年1月29日14:17:57
 */
@Configuration
public class SwaggerUIConfig {
    /**
     * Swagger 2 配置
     * @return
     */
    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Time Wheel Manager API")
                .contact(new Contact("", "", ""))
                .version("0.1").description("Geek时间轮任务调度系统接口文档").build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.geek.wheel.controller"))
                .paths(PathSelectors.any()).build();
    }
}
