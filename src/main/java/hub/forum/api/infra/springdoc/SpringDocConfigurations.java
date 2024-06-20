package hub.forum.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                        .info(new Info()
                                .title("Fórum.hub API")
                                .description("O FórumHub é uma API REST desenvolvida em Java utilizando Spring Boot, \n" +
                                        "que simula um fórum de discussão. Nesta API, os usuários podem criar, \n" +
                                        "visualizar, atualizar e deletar tópicos de discussão, perfis, respostas, \n" +
                                        "cursos e usuários. A partir de agora, apenas usuários autenticados podem \n" +
                                        "interagir com a API. Este projeto é parte do desafio Challenge Back End.")
                                .contact(new Contact()
                                        .name("Time Backend")
                                        .email("dcotrimmacedo@gmail.com"))
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://forum.hub/api/licenca")));
    }
}
