package com.store.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().servers(getServer()).info(new Info()
                .title("Retail Store API")
                .version("1.0")
                .description(
                        "Descripción de la API: Desde aqui puedes consultar la documentación donde se ejemplifica  el consumo de las APIS existentes en la aplicación"));

    }

    private List<Server> getServer() {
        return List.of(
                new Server().url("http://localhost:8080").description("Servidor Pruebas Locales"),
                new Server().url("http://192.168.1.174:8080").description("Servidor QA"));
    }
}
