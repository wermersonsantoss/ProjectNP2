package com.securitysystem.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class SwaggerConfig {

  /*  @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Security System API")
                        .version("1.0")
                        .description("Documentação da API do Sistema de Segurança"))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"));
    }
*/

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Security System API")
                        .version("1.0")
                        .description("Documentação da API do Sistema de Segurança"));
    }
}	    


