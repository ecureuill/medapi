package ecureuill.medapi.infra.doc;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SpringDocConfig {
    @Bean
    public GroupedOpenApi doctorsApi() {
        return GroupedOpenApi.builder()
                .group("doctors")
                .pathsToMatch("/doctors/**")
                .build();
    }

    @Bean
    public GroupedOpenApi patientApi() {
        return GroupedOpenApi.builder()
                .group("patients")
                .pathsToMatch("/patients/**")
                .build();
    }

    @Bean
    public GroupedOpenApi appointmentApi() {
        return GroupedOpenApi.builder()
                .group("appointments")
                .pathsToMatch("/appointments/**")
                .build();
    }

    @Bean
    public GroupedOpenApi loginApi() {
        return GroupedOpenApi.builder()
                .group("login")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi welcomeApi() {
        return GroupedOpenApi.builder()
                .group("ping")
                .pathsToMatch("/ping/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                    .addSecuritySchemes("bearer-key",
                    new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                    .title("Voll.med API")
                    .description("API Rest da aplicação Voll.med, contendo as funcionalidades de CRUD de médicos e de pacientes, além de agendamento e cancelamento de consultas")
                    .contact(new Contact()
                        .name("Time Backend")
                        .email("backend@voll.med"))
                    .license(new License()
                            .name("Apache 2.0")
                            .url("http://voll.med/api/licenca"))
                );
    }
}