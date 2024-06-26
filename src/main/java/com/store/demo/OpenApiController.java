package com.store.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api-docs")
public class OpenApiController {

    @GetMapping(value = "/swagger.yaml", produces = "application/yaml")
    public Resource getSwaggerYaml() {
        return new ClassPathResource("swagger.yaml");
    }
}
