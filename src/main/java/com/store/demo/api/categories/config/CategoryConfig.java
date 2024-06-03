package com.store.demo.api.categories.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
                "com.store.demo.api.categories"
})
@EnableJpaRepositories(basePackages = {
                "com.store.demo.api.categories.repository"
}, entityManagerFactoryRef = "H2EntityManagerFactory", transactionManagerRef = "H2TransactionManager")

@EntityScan(basePackages = {
                "com.store.demo.api.categories.entity"
})
public class CategoryConfig {
        // Aqui va de forma opcional configuracion para el modulo
}
